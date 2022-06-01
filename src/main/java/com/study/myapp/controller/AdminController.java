package com.study.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

    @RequestMapping(value = "/{param}", method = RequestMethod.GET)
    public String index(HttpServletRequest request, @PathVariable("param") String param) {
        if(param != null && param.isEmpty() != true) {
            return "/screen/" + request.getRequestURI();
        }else {
            return "/screen/index";
        }
    }

    //    @RequestMapping(value = "/{param1}/{param2}", method = RequestMethod.GET)
    //    public String index(HttpServletRequest request, @PathVariable("param1") String param1, @PathVariable("param2") String param2) {
    //        return "/screens/" + param1 + "/" + param2;
    //    }
}
