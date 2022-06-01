package com.study.myapp.service.member;

import com.study.myapp.mapper.member.MemberMpp;
import com.study.myapp.model.member.MemberModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private MemberMpp memberMpp;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        MemberModel memberModel = memberMpp.findByUserId(userId);
        if(memberModel != null) {
            return new UserDetail(memberModel);
        }
        return null;

        //MemberModel UserRole = memberMpp.findByUserId(userId);
        //if(UserRole != null && UserRole.getRoleNames().isEmpty() == false) {
        //    String[] RoleNames = UserRole.getRoleNames().split("\\||\\,");
        //    int i;
        //    String RoleNm = "";
        //    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        //    for (i=0; i < RoleNames.length; i++) {
        //        if (i%2 == 0) {
        //            continue;
        //        }
        //        RoleNm = RoleNames[i];
        //        roles.add(new SimpleGrantedAuthority(RoleNm));
        //    }
        //    return new User(userId, UserRole.getUserPswd(), roles);
        //}

        //if(RoleList != null && RoleList.size() > 0) {
        //    int i;
        //    String RoleNm = "";
        //    List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
        //    for(i=0; i< RoleList.size(); i++){
        //        RoleNm = RoleList.get(i).getRoleName().trim();
        //        if (RoleNm.equals("")) {
        //            continue;
        //        }
        //        roles.add(new SimpleGrantedAuthority(RoleNm));
        //    }
        //    return new User(userId, RoleList.get(0).getUserPswd(), roles);
        //}
    }
}
