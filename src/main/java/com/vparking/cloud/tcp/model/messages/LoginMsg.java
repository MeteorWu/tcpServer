package com.vparking.cloud.tcp.model.messages;

/**
 * Created by Administrator on 2017/8/11 0011.
 */
public class LoginMsg extends BaseMsg {

    private String password;

    public LoginMsg (){
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
