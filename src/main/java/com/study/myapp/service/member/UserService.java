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
        List<MemberModel> RoleList = memberMpp.findByUserId(userId);

        if(RoleList != null && RoleList.size() > 0) {
            int i;
            String RoleNm = "";
            List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
            for(i=0; i< RoleList.size(); i++){
                RoleNm = RoleList.get(i).getRoleName().trim();
                if (RoleNm.equals("")) {
                    continue;
                }
                roles.add(new SimpleGrantedAuthority(RoleNm));
            }
            return new User(userId, userId, roles);
        }
        return null;
    }
}
