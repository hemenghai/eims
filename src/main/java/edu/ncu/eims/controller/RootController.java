package edu.ncu.eims.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hemenghai
 * @date 2019-02-25
 */
@Controller
@RequestMapping("/")
public class RootController {

    @GetMapping
    public String mainPage(){
        return "forward:/index.html";
    }
}
