package com.study.myapp.service.admin;

import com.study.myapp.model.admin.CodeModel;
import com.study.myapp.model.admin.RoleModel;

import java.util.List;

public interface RoleService {
    List<RoleModel> getRoleList(RoleModel roleModel);
    RoleModel getRole(RoleModel roleModel);
    int setRoleAdd(RoleModel roleModel);
    int setRoleModify(RoleModel roleModel);
    int setRoleDelete(RoleModel roleModel);
}
