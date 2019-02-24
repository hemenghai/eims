package edu.ncu.eims.controller;

import edu.ncu.eims.entity.Enterprise;
import edu.ncu.eims.service.EnterpriseService;
import edu.ncu.eims.util.ResponseData;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hemenghai
 * @date 2019-02-23
 */
@Api
@RestController
@RequestMapping("/enterprise")
public class EnterpriseController {

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
}
