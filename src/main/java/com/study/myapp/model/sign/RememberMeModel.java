package com.study.myapp.model.sign;

import lombok.Data;

import java.util.Date;

@Data
public class RememberMeModel {
    private String series;
    private String username;
    private String token;
    private Date lastUsed;
}
