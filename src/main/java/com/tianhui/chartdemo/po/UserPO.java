package com.tianhui.chartdemo.po;

import com.tianhui.chartdemo.bean.UserBean;

public class UserPO extends UserBean {
    private String password;
    private String open_id;
    private Integer role_id;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }


    @Override
    public String toString() {
        return  super.toString()+"UserPO{" +
                "password='" + password + '\'' +
                ", open_id='" + open_id + '\'' +
                ", role_id=" + role_id +
                '}';
    }
}
