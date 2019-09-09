package com.tianhui.chartdemo.vo;

import com.tianhui.chartdemo.bean.UserBean;

public class UserVO extends UserBean {

    private String role_name;
    private String common_address;

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public String getCommon_address() {
        return common_address;
    }

    public void setCommon_address(String common_address) {
        this.common_address = common_address;
    }

    @Override
    public String toString() {
        return  super.toString()+"UserPO{" +
                "role_name='" + role_name + '\'' +
                ", common_address='" + common_address + '\'' +
                '}';
    }
}
