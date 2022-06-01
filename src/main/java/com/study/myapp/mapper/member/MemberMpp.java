package com.study.myapp.mapper.member;

import com.study.myapp.model.member.MemberModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
//@Repository
public interface MemberMpp {
    // 로그인
    MemberModel findByUserId(@Param("UserId") String UserId);

    //유저 저장
    int userSave(MemberModel memberModel);
}