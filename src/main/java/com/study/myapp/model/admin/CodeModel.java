package com.study.myapp.model.admin;

import com.study.myapp.model.comm.MessageModel;
import lombok.Data;

@Data
public class CodeModel {
    private Integer RowNum;
    private String GroupNm;
    private String Code;
    private String CodeName;
    private String CodeOptn01;
    private String CodeOptn02;
    private String CodeOptn03;
    private String CodeDesc;
    private Integer Sort;
    private String DelYn;
    private String Del;
    private String CreateName;
    private String CreateDate;
    private Integer CreateUserPid;
    private String CreateUserId;
    private String ModifyName;
    private String ModifyDate;
    private Integer ModifyUserPid;
    private String ModifyUserId;
    //private MessageModel messageModel;
}
