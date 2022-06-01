package com.study.myapp.controller;

import com.study.myapp.mapper.member.MemberMpp;
import com.study.myapp.service.member.UserDetail;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class MemberController {
    // 로그인 폼
    @GetMapping("/signIn")
    public String signIn(Model model) throws Exception {
        return "/screen/member/signIn";
    }

    // 로그인 진행
    @RequestMapping("/signInProc")
    public String signInProc(Model model) throws Exception {
        String Path = "";
        try {
            Path = "/screen/member/signIn";
        } catch (Exception e) {
            Path = "/screen/error/403";
        }
        return  Path;
    }

    // 로그인 에러
    @RequestMapping("/signFailure")
    public String signFailure(Model model) {
        model.addAttribute("sign_error_msg", "Invalid username and password.");
        return "/screen/member/signIn";
    }

    // 로그인 성공 후 접근
    @GetMapping("/signAccess")
    public String sign_access(Model model, Authentication authentication) {
        //UserDetail userDetail = (UserDetail)authentication.getPrincipal();
        //model.addAttribute("sign_info", userDetail.getUsername());
        return "redirect:/home/home";
    }

    // 회원가입
    @GetMapping("/signUp")
    public String signUn(Model model) throws Exception {
        return "/screen/member/signUp";
    }
    
    // 403
    @PostMapping("/error403")
    public String error403(Model model) {
        return "/error/403";
    }
    // 404
    @PostMapping("/error404")
    public String error404(Model model) {
        return "/error/404";
    }
    // 500
    @PostMapping("/error500")
    public String error500(Model model) {
        return "/error/500";
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
