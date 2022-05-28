package com.study.myapp.service.member;

import com.study.myapp.model.member.MemberModel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetail implements UserDetails {
    private MemberModel member;

    public UserDetail(MemberModel member) {
        this.member = member;
    }

    public MemberModel getUser() {
        return this.member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return member.getRoleName();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        //return member.get(0).getPswd();
        return member.getUserPswd();
    }

    @Override
    public String getUsername() {
        //return member.get(0).getNm();
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
