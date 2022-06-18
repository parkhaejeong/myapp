package com.study.myapp.service.admin.impl;

import com.study.myapp.mapper.admin.CodeMpp;
import com.study.myapp.model.admin.CodeModel;
import com.study.myapp.service.admin.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CodeServiceImpl implements CodeService {
    @Autowired
    private CodeMpp codeMpp;

    @Override
    public List<CodeModel> getCodeList(CodeModel codeModel) {
        return codeMpp.getCodeList(codeModel);
    }
    @Override
    public CodeModel getCode(CodeModel codeModel) {
        return codeMpp.getCode(codeModel);
    }
    @Override
    public int setCodeAdd(CodeModel codeModel) {
        return codeMpp.setCodeAdd(codeModel);
    }
    @Override
    public int setCodeModify(CodeModel codeModel) {
        return codeMpp.setCodeModify(codeModel);
    }
    @Override
    public int setCodeDelete(CodeModel codeModel) {
        return codeMpp.setCodeDelete(codeModel);
    }
}


