package com.vparking.cloud.tcp.model;

import java.util.List;

/**
 * Created by Administrator on 2017/8/11 0011.
 */
public class LoginMsg extends BaseMsg {

    private String password;

    public LoginMsg (List<String> list){
        super(list);
        password = list.get(3);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
