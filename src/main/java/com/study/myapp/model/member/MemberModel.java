package com.study.myapp.model.member;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private String CreateDate;
    private Integer CreateUserPid;
    private String ModifyDate;
    private Integer ModifyUserPid;
    private Integer RolePid;
    private String RoleName;
    private String RoleNames;
}
