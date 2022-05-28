package com.study.myapp.service.home;

import com.study.myapp.model.home.HomeModel;

import java.util.List;

public interface HomeService {
    List<HomeModel> getHomeData(HomeModel homeModel);
}
