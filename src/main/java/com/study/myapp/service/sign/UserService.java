package com.study.myapp.service.sign;

import com.study.myapp.mapper.sign.SignMpp;
import com.study.myapp.model.sign.SignModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private SignMpp signMpp;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        SignModel memberModel = signMpp.findByUserId(userId);
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
