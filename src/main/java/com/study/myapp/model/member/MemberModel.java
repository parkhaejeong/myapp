package com.study.myapp.model.member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberModel {
    private Integer UserPid;
    private String UserId;
    private String UserPswd;
    private String UserName;
    private String Brdt;
    private String Gender;
    private String Email;
    private String TelNo1;
    private String TelNo2;
    private String Addr;
    private String DetailAddr;
    private String Zip;
    private String DelYn;
    private String CreatDt;
    private Integer CreatPid;
    private String ModifyDt;
    private Integer ModifyPid;
    private String RoleName;
}
