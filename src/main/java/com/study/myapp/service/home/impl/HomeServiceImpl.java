package com.study.myapp.service.home.impl;

import com.study.myapp.mapper.home.HomeMpp;
import com.study.myapp.model.home.HomeModel;
import com.study.myapp.service.home.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private HomeMpp homeMpp;

    @Override
    public List<HomeModel> getHomeData(HomeModel homeModel) {
        List<HomeModel> homeData = homeMpp.getHomeData(homeModel);
        return homeData;
    }
}
