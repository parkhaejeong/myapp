package com.study.myapp.mapper.member;

import com.study.myapp.model.member.MemberModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMpp {
    MemberModel findByUserId(@Param("id") String id);

    //유저 저장
    int userSave(MemberModel memberModel);
}