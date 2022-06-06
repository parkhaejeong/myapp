package com.study.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FragmentController {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() { return "index";}

    @RequestMapping("/error_403")
    public String error_403() {
        return "/screen/error/403";
    }

    @RequestMapping("/error_404")
    public String error_404() {
        return "/screen/error/404";
    }

    @RequestMapping("/error_500")
    public String error_500() {
        return "/screen/error/500";
    }
}
