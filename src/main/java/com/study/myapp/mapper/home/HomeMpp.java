package com.study.myapp.mapper.home;

import com.study.myapp.model.home.HomeModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface HomeMpp {
    List<HomeModel> getHomeData(HomeModel homeModel);
}
