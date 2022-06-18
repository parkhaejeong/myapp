package com.study.myapp.controller;

import com.study.myapp.controller.common.StaticData;
import com.study.myapp.model.admin.CodeModel;
import com.study.myapp.service.admin.CodeService;
import com.study.myapp.service.sign.UserDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(value = "admin/code")
public class CodeController {
    @Autowired
    private CodeService codeService;
    @RequestMapping(value = "/{param}", method = RequestMethod.GET)
    public String index(HttpServletRequest request, @PathVariable("param") String param) {
        if(param != null && param.isEmpty() != true) {
            return "/screen/" + request.getRequestURI();
        }else {
            return "/screen/index";
        }
    }
    // 코드 리스트 조회
    @RequestMapping(value = "/codeList/getCodeList", method = RequestMethod.POST)
    public String getCodeList(HttpServletRequest request, CodeModel codeModel, Model model) throws Exception {
        model.addAttribute("codeList", this.codeService.getCodeList(codeModel));
        return StaticData.index(request) + ":: #codeListData";
    }
    // 코드 조회
    @RequestMapping(value = "/codeWrite/getCode", method = RequestMethod.POST)
    public String getCode(HttpServletRequest request, CodeModel codeModel, Model model) throws Exception {
        model.addAttribute("code", this.codeService.getCode(codeModel));
        return StaticData.index(request) + ":: #codeData";
    }
    // 코드 등록
    @RequestMapping(value = "/codeWrite/setCodeAdd", method = RequestMethod.POST)
    public String setCodeAdd(HttpServletRequest request, CodeModel codeModel, Model model, @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        codeModel.setModifyUserPid(userDetail.getMember().getUserPid());
        codeModel.setCreateUserPid(userDetail.getMember().getUserPid());
        this.codeService.setCodeAdd(codeModel);
        return StaticData.index(request) ;
    }
    // 코드 수정
    @RequestMapping(value = "/codeWrite/setCodeModify", method = RequestMethod.POST)
    public String setCodeModify(HttpServletRequest request, CodeModel codeModel, Model model, @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        codeModel.setModifyUserPid(userDetail.getMember().getUserPid());
        codeModel.setCreateUserPid(userDetail.getMember().getUserPid());
        this.codeService.setCodeModify(codeModel);
        return StaticData.index(request) ;
    }
    // 코드 삭제
    @RequestMapping(value = {"/codeList/setCodeDelete", "/codeWrite/setCodeDelete"}, method = RequestMethod.POST)
    public String setCodeDelete(HttpServletRequest request, CodeModel codeModel, Model model, @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        codeModel.setModifyUserPid(userDetail.getMember().getUserPid());
        codeModel.setCreateUserPid(userDetail.getMember().getUserPid());
        this.codeService.setCodeDelete(codeModel);
        return StaticData.index(request) ;
    }
}
