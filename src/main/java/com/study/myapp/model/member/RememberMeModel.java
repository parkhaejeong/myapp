package com.study.myapp.model.member;

import lombok.Data;

import java.util.Date;

@Data
public class RememberMeModel {
    private String series;
    private String username;
    private String token;
    private Date lastUsed;
}
