package edu.ncu.eims.controller;

import edu.ncu.eims.entity.Enterprise;
import edu.ncu.eims.service.EnterpriseService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Page<Enterprise> query(@RequestParam Integer page, @RequestParam Integer size){
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.ASC, "enterpriseId");
        return enterpriseService.queryPage(pageable);
    }
}
