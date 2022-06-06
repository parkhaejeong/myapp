package com.study.myapp.mapper.sign;

import com.study.myapp.model.sign.SignModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
//@Repository
public interface SignMpp {
    // 로그인
    SignModel findByUserId(@Param("UserId") String UserId);

    //유저 저장
    int userSave(SignModel memberModel);
}