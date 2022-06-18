package com.study.myapp.mapper.admin;

import com.study.myapp.model.admin.CodeModel;
import com.study.myapp.model.home.HomeModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CodeMpp {
    List<CodeModel> getCodeList(CodeModel codeModel);
    CodeModel getCode(CodeModel codeModel);
    int setCodeAdd(CodeModel codeModel);
    int setCodeModify(CodeModel codeModel);
    int setCodeDelete(CodeModel codeModel);
}
