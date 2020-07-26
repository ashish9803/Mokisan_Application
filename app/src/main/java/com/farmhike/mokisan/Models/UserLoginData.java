package com.farmhike.mokisan.Models;

public class UserLoginData {
    private static UserLoginData obj;
    private String phoneNo;

    private UserLoginData() {
    }

    public static UserLoginData getInstance() {
        if (obj == null) {
            obj = new UserLoginData();
            return obj;
        } else
            return obj;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
