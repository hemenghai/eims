package edu.ncu.eims.controller;

import edu.ncu.eims.dto.request.AddEnterpriseDto;
import edu.ncu.eims.dto.request.UpdateEnterpriseDto;
import edu.ncu.eims.entity.Enterprise;
import edu.ncu.eims.service.EnterpriseService;
import edu.ncu.eims.util.FileUtils;
import edu.ncu.eims.util.ResponseData;
import edu.ncu.eims.util.ResponseListData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.CellType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * @author hemenghai
 * @date 2019-02-23
 */
@Api("企业操作接口")
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    private static final String TEMP_DIRTIONARY = ".tmp";

    private static final String TEMPLATE_NAME = "enterprise.xls";
    private static final String TEMPLATE_FILE_PATH = ".files/enterprise.xls";
    private static final String TEMPLATE_RESOURCE_PATH = "static/xls/enterprise.xls";

    private static final String[] HEADER_NAMES = {"企业名称", "统一编码", "企业规模", "法人代表", "成立时间", "联系号码", "行业类别", "行业名称", "产业链环节", "主营业务一", "主营业务二"};

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping("/query")
    @ApiOperation("分页查询")
    public ResponseListData<Enterprise> query(@RequestParam Integer page,
                                              @RequestParam Integer size,
                                              @RequestParam(required = false) String name,
                                              @RequestParam(required = false) String scale,
                                              @RequestParam(required = false) String category,
                                              @RequestParam(required = false) String chain){
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.ASC, Enterprise.ENTERPRISE_NAME);
        return ResponseListData.of(enterpriseService.queryPage(name, scale, category, chain, pageable));
    }

    @GetMapping("/details/{id}")
    @ApiOperation("查询详情")
    public ResponseData<Enterprise> get(@PathVariable("id") String id){
        return ResponseData.ok(enterpriseService.get(id));
    }

    @PostMapping
    @ApiOperation("添加")
    public ResponseData<Void> add(@RequestBody AddEnterpriseDto dto){
        enterpriseService.save(dto.toEntity());
        return new ResponseData<>();
    }

    @PutMapping
    @ApiOperation("更新")
    public ResponseData<Void> update(@RequestBody UpdateEnterpriseDto dto){
        enterpriseService.update(dto.toEntity());
        return new ResponseData<>();
    }

    @DeleteMapping
    @ApiOperation("删除")
    public ResponseData<Void> delete(@RequestBody String ids){
        enterpriseService.deleteByIds(ids.split(","));
        return new ResponseData<>();
    }

    @PostMapping("/import")
    @ApiOperation("导入")
    public ResponseData<Void> importFile(@RequestParam("file") MultipartFile file) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
        HSSFSheet sheet = workbook.getSheetAt(0);

        HSSFRow row;
        HSSFCell cell;

        List<Enterprise> enterprises = new LinkedList<>();
        for (int i = 1; i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            Enterprise enterprise = new Enterprise();

            enterprise.setEnterpriseId(UUID.randomUUID().toString());
            enterprise.setEnterpriseName(row.getCell(0).getStringCellValue());
            cell = row.getCell(1);
            cell.setCellType(CellType.STRING);
            enterprise.setEnterpriseNumber(cell.getStringCellValue());
            enterprise.setEnterpriseScale(row.getCell(2).getStringCellValue());
            enterprise.setLegalPerson(row.getCell(3).getStringCellValue());
            cell = row.getCell(4);
            cell.setCellType(CellType.STRING);
            enterprise.setEstablishmentTime(cell.getStringCellValue());
            cell = row.getCell(5);
            cell.setCellType(CellType.STRING);
            enterprise.setContactNumber(cell.getStringCellValue());
            enterprise.setIndustryCategory(row.getCell(6).getStringCellValue());
            enterprise.setIndustryName(row.getCell(7).getStringCellValue());
            enterprise.setIndustryChain(row.getCell(8).getStringCellValue());
            enterprise.setMainBusiness1(row.getCell(9).getStringCellValue());
            enterprise.setMainBusiness2(row.getCell(10).getStringCellValue());

            enterprises.add(enterprise);
        }
        enterpriseService.saveAll(enterprises);
        return new ResponseData<>();
    }

    @GetMapping("/export")
    @ApiOperation("导出")
    public ResponseEntity<byte[]> exportData() throws IOException {
        List<Enterprise> list = enterpriseService.getAll();
        HSSFWorkbook workbook = new HSSFWorkbook();

        HSSFSheet sheet = workbook.createSheet("企业信息表");

        HSSFRow row;
        // 标记行数
        int cnt = 0;

        row = sheet.createRow(cnt++);
        for (int i = 0; i < HEADER_NAMES.length; i++) {
            row.createCell(i).setCellValue(HEADER_NAMES[i]);
        }

        // 建立主体部分
        for (Enterprise enterprise: list) {
            row = sheet.createRow(cnt++);
            row.createCell(0).setCellValue(enterprise.getEnterpriseName());
            row.createCell(1).setCellValue(enterprise.getEnterpriseNumber());
            row.createCell(2).setCellValue(enterprise.getEnterpriseScale());
            row.createCell(3).setCellValue(enterprise.getLegalPerson());
            row.createCell(4).setCellValue(enterprise.getEstablishmentTime());
            row.createCell(5).setCellValue(enterprise.getContactNumber());
            row.createCell(6).setCellValue(enterprise.getIndustryCategory());
            row.createCell(7).setCellValue(enterprise.getIndustryName());
            row.createCell(8).setCellValue(enterprise.getIndustryChain());
            row.createCell(9).setCellValue(enterprise.getMainBusiness1());
            row.createCell(10).setCellValue(enterprise.getMainBusiness2());
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", new String("企业信息.xls".getBytes("UTF-8"), "iso-8859-1"));
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.CREATED);
    }


    @GetMapping("/download")
    @ApiOperation("下载模版")
    public String template(HttpServletResponse response) throws UnsupportedEncodingException {
        File file = new File(TEMPLATE_FILE_PATH);
        if(!file.exists()){
            FileUtils.copyFromResource(TEMPLATE_FILE_PATH, TEMPLATE_RESOURCE_PATH, this.getClass().getClassLoader());
        }

        response.setContentType("application/octet-stream");
        response.setHeader("content-type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(TEMPLATE_NAME, "UTF-8"));
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream(file))){
            OutputStream out = response.getOutputStream();
            StreamUtils.copy(in, out);
            return "下载成功";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "下载失败";
    }
}
