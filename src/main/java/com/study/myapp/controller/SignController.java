package com.study.myapp.controller;

import com.study.myapp.service.sign.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class SignController {
    // 로그인 폼
    @GetMapping("/signIn")
    public String signIn(Model model) throws Exception {
        return "/screen/sign/signIn";
    }

    // 로그인 진행
    @RequestMapping("/signInProc")
    public String signInProc(Model model) throws Exception {
        return "/screen/sign/signIn";
    }

    // 로그인 에러
    @RequestMapping("/signFailure")
    public String signFailure(Model model) {
        model.addAttribute("sign_error_msg", "Invalid username and password.");
        return "/screen/sign/signIn";
    }

    // 로그인 성공 후 접근
    @GetMapping("/signAccess")
    public String sign_access(Model model, Authentication authentication) {
        //UserDetail userDetail = (UserDetail)authentication.getPrincipal();
        //model.addAttribute("sign_info", userDetail.getUsername());

        //return "redirect:/home/home";
        return "redirect:/";
    }

    // 회원가입
    @GetMapping("/signUp")
    public String signUp(Model model) throws Exception {
        return "/screen/sign/signUp";
    }

    // 로그인 정보
    @GetMapping("/signInfo")
    @ResponseBody
    public String sign_info(Authentication authentication, @AuthenticationPrincipal UserDetail userDetail){
        UserDetail principal = (UserDetail) authentication.getPrincipal();
        System.out.println(principal.getUsername());
        System.out.println(principal.getPassword());
        System.out.println(principal.getAuthorities());
        //MemberModel memberModel = principal.getUsername();
        //System.out.println(memberModel);
        //MemberModel memberModel1 = userDetail.getUsername();
        //System.out.println(memberModel1);
        return  principal.getUsername();
    }
}
