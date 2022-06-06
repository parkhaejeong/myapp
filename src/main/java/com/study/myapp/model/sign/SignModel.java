package com.study.myapp.model.sign;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SignModel {
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
    private String CreateDate;
    private Integer CreateUserPid;
    private String ModifyDate;
    private Integer ModifyUserPid;
    private Integer RolePid;
    private String RoleName;
    private String RoleNames;
}
