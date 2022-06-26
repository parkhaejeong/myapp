package com.study.myapp.controller;

import com.study.myapp.controller.common.StaticData;
import com.study.myapp.controller.common.StaticMessage;
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
import java.util.List;

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
    public String getCodeList(HttpServletRequest request,
                              CodeModel codeModel, Model model) throws Exception {
        List<CodeModel> codeList = this.codeService.getCodeList(codeModel);
        if (codeList == null) {
            model.addAttribute("codeList", null);
            model.addAttribute("msg", StaticMessage.message(0,"코드리스트조회"));
        } else {
            model.addAttribute("codeList", codeList);
            model.addAttribute("msg", StaticMessage.message(200,"코드리스트조회"));
        }
        return StaticData.index(request) + ":: #CommData";
    }
    // 코드 조회
    @RequestMapping(value = "/codeWrite/getCode", method = RequestMethod.POST)
    public String getCode(HttpServletRequest request,
                          CodeModel codeModel, Model model) throws Exception {
        CodeModel code = this.codeService.getCode(codeModel);
        if (code == null) {
            model.addAttribute("code", null);
            model.addAttribute("msg", StaticMessage.message(0,"코드조회"));
        } else {
            model.addAttribute("code", code);
            model.addAttribute("msg", StaticMessage.message(200,"코드조회"));
        }
        return StaticData.index(request) + ":: #CommData";
    }
    // 코드 등록
    @RequestMapping(value = "/codeWrite/setCodeAdd", method = RequestMethod.POST)
    public String setCodeAdd(HttpServletRequest request,
                             CodeModel codeModel, Model model,
                             @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        codeModel.setModifyUserPid(userDetail.getMember().getUserPid());
        codeModel.setCreateUserPid(userDetail.getMember().getUserPid());
        int iResult = this.codeService.setCodeAdd(codeModel);
        if (iResult == 1) {
            model.addAttribute("msg", StaticMessage.message(200,"코드등록"));
        } else {
            model.addAttribute("msg", StaticMessage.message(0,"코드등록"));
        }
        return StaticData.index(request) + ":: #CommData";
    }
    // 코드 수정
    @RequestMapping(value = "/codeWrite/setCodeModify", method = RequestMethod.POST)
    public String setCodeModify(HttpServletRequest request,
                                CodeModel codeModel, Model model,
                                @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        codeModel.setModifyUserPid(userDetail.getMember().getUserPid());
        codeModel.setCreateUserPid(userDetail.getMember().getUserPid());
        int iResult = this.codeService.setCodeModify(codeModel);
        if (iResult == 1) {
            model.addAttribute("msg", StaticMessage.message(200,"코드수정"));
        } else {
            model.addAttribute("msg", StaticMessage.message(0,"코드수정"));
        }
        return StaticData.index(request) + ":: #CommMsgData";
    }
    // 코드 삭제
    @RequestMapping(value = {"/codeList/setCodeDelete", "/codeWrite/setCodeDelete"}, method = RequestMethod.POST)
    public String setCodeDelete(HttpServletRequest request,
                                CodeModel codeModel, Model model,
                                @AuthenticationPrincipal UserDetail userDetail) throws Exception {
        codeModel.setModifyUserPid(userDetail.getMember().getUserPid());
        codeModel.setCreateUserPid(userDetail.getMember().getUserPid());
        int iResult = this.codeService.setCodeDelete(codeModel);
        if (iResult == 1) {
            model.addAttribute("msg", StaticMessage.message(200,"코드삭제"));
        } else {
            model.addAttribute("msg", StaticMessage.message(0,"코드삭제"));
        }
        return StaticData.index(request) + ":: #CommMsgData";
    }
}
