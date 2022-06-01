package com.study.myapp.controller;

import com.study.myapp.controller.common.StaticData;
import com.study.myapp.mapper.home.HomeMpp;
import com.study.myapp.model.home.HomeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(value = "my")
public class MyController {

    @Autowired
    private HomeMpp homeMpp;

    @RequestMapping(value = "/{param}", method = RequestMethod.GET)
    public String index(HttpServletRequest request, @PathVariable("param") String param) {
        if(param != null && param.isEmpty() != true) {
            return "/screen/" + request.getRequestURI();
        }else {
            return "/screen/index";
        }
    }

    @RequestMapping(value = "/getMyInfo", method = RequestMethod.POST)
    public String getMyInfo(HttpServletRequest request, HomeModel homeModel, Model model) throws Exception {
        List<HomeModel> HomeData = this.homeMpp.getHomeData(homeModel);
        model.addAttribute("Data", HomeData);
        return StaticData.index(request) + " :: #Data";
    }

}
