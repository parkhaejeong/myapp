package com.study.myapp.service.member;

import com.study.myapp.mapper.member.MemberMpp;
import com.study.myapp.model.member.MemberModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    //private MemberMapper memberMapper;
    private MemberMpp memberMpp;

    //@Transactional
    //public void joinUser(MemberModel user){
    //    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //    user.setPswd(passwordEncoder.encode(user.getPswd()));
    //    memberMapper.userSave(user);
    //}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //ArrayList<MemberModel> userAuthes = memberMapper.findByUserId(id);
        //if(userAuthes.size() == 0) {
        //    throw new UsernameNotFoundException("User [" + id + "] Not Found!");
        //}
        //return new UserDetail(userAuthes);

        MemberModel byUsername = memberMpp.findByUserId(username);

        if(byUsername != null) {
            return new UserDetail(byUsername);
        }
        return null;
    }
}
