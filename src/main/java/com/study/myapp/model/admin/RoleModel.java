package com.study.myapp.model.admin;

import lombok.Data;

@Data
public class RoleModel {
    private Integer RowNum;
	private Integer RolePid;
	private String RoleName;
    private String DelYn;
    private String Del;
    private String CreateName;
    private String CreateDate;
    private Integer CreateUserPid;
    private String ModifyName;
    private String ModifyDate;
    private Integer ModifyUserPid;
}
