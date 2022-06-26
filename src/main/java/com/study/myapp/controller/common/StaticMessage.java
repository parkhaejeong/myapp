package com.study.myapp.controller.common;

import com.study.myapp.model.comm.MessageModel;

public class StaticMessage {
    private static MessageModel messageModel = null;

    public static String messageCode(int number) {
        String temp = "";
        switch (number){
            case 200 :
                temp = "작업을 성공했습니다!";
                break;
            case 0 :
                temp = "작업을 실패했습니다!";
                break;
            default :
                temp = "정의 할 코드값이 없습니다!";
                break;            
        }
        return temp;
    }

    public static MessageModel message(int number, String title) {
        messageModel = new MessageModel();
        messageModel.setStatus(number);
        messageModel.setTitle(title);
        messageModel.setMessage(messageCode(number));
        return messageModel;
    }
}
