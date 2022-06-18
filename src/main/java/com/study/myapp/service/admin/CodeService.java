package com.study.myapp.service.admin;

import com.study.myapp.model.admin.CodeModel;
import java.util.List;

public interface CodeService {
    List<CodeModel> getCodeList(CodeModel codeModel);
    CodeModel getCode(CodeModel codeModel);
    int setCodeAdd(CodeModel codeModel);
    int setCodeModify(CodeModel codeModel);
    int setCodeDelete(CodeModel codeModel);
}
