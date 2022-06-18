package com.study.myapp.service.admin.impl;

import com.study.myapp.mapper.admin.RoleMpp;
import com.study.myapp.model.admin.RoleModel;
import com.study.myapp.service.admin.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMpp roleMpp;

    @Override
    public List<RoleModel> getRoleList(RoleModel roleModel) {
        return roleMpp.getRoleList(roleModel);
    }
    @Override
    public RoleModel getRole(RoleModel roleModel) {
        return roleMpp.getRole(roleModel);
    }
    @Override
    public int setRoleAdd(RoleModel roleModel) {
        return roleMpp.setRoleAdd(roleModel);
    }
    @Override
    public int setRoleModify(RoleModel roleModel) {
        return roleMpp.setRoleModify(roleModel);
    }
    @Override
    public int setRoleDelete(RoleModel roleModel) {
        return roleMpp.setRoleDelete(roleModel);
    }

}
