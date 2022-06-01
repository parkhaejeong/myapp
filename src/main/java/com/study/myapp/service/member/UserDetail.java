package com.study.myapp.service.member;

import com.study.myapp.model.member.MemberModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {
    private MemberModel member;

    public UserDetail(MemberModel member) {
        this.member = member;
    }

    public MemberModel getMember() {
        return this.member;
    }

    //public String getUserId() {
    //    return member.getUserId();
    //}
    //public String getUserPswd() {
    //    return member.getUserName();
    //}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        int i;
        String RoleNm = "";
        String[] RoleNames = member.getRoleNames().split("\\||\\,");
        Collection<GrantedAuthority> collect = new ArrayList<>();
        for (i=0; i < RoleNames.length; i++) {
            if (i%2 == 0) {
                continue;
            }
            RoleNm = RoleNames[i];
            collect.add(new SimpleGrantedAuthority(RoleNm));
        }
        return collect;

        //Collection<GrantedAuthority> collect = new ArrayList<>();
        //collect.add(new GrantedAuthority() {
        //    @Override
        //    public String getAuthority() {
        //        return member.getRoleName();
        //    }
        //});
        //return collect;
    }

    @Override
    public String getPassword() {
        return member.getUserPswd();
    }

    @Override
    public String getUsername() {
        return member.getUserName();
    }

    @Override
    //계정 만료 여부(false = 만료)
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    //계정 잠김 여부(false = 잠김)
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //계정 비밀번호 만료 여부(false = 만료)
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    //계정 활성화 여부(false = 비활성)
    public boolean isEnabled() {
        return true;
    }
}
