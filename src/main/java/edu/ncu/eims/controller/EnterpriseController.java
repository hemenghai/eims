package edu.ncu.eims.controller;

import edu.ncu.eims.entity.Enterprise;
import edu.ncu.eims.service.EnterpriseService;
import edu.ncu.eims.util.FileUtils;
import edu.ncu.eims.util.ResponseData;
import io.swagger.annotations.Api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

/**
 * @author hemenghai
 * @date 2019-02-23
 */
@Api
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

    private static final String TEMPLATE_NAME = "enterprise.xls";
    private static final String TEMPLATE_FILE_PATH = "files/enterprise.xls";
    private static final String TEMPLATE_RESOURCE_PATH = "static/xls/enterprise.xls";

    @Autowired
    private EnterpriseService enterpriseService;

    @GetMapping
    public ResponseData<Enterprise> query(@RequestParam Integer page,
                                          @RequestParam Integer size,
                                          @RequestParam(required = false) String name,
                                          @RequestParam(required = false) String scale,
                                          @RequestParam(required = false) String category,
                                          @RequestParam(required = false) String chain){
        Pageable pageable = PageRequest.of(page-1, size, Sort.Direction.ASC, Enterprise.ENTERPRISE_ID);
        return ResponseData.of(enterpriseService.queryPage(name, scale, category, chain, pageable));
    }

    @GetMapping("/template")
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
