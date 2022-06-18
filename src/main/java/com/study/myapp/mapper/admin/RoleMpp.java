package com.study.myapp.mapper.admin;

import com.study.myapp.model.admin.RoleModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface RoleMpp {
    List<RoleModel> getRoleList(RoleModel roleModel);
    RoleModel getRole(RoleModel roleModel);
    int setRoleAdd(RoleModel roleModel);
    int setRoleModify(RoleModel roleModel);
    int setRoleDelete(RoleModel roleModel);
}
