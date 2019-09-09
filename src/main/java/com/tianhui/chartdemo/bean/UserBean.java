package com.tianhui.chartdemo.bean;

import com.tianhui.chartdemo.base.BasicEntity;

public class UserBean extends BasicEntity {
    private String name;
    private String nickname;
    private String gender;
    private String icon_url;
    private String telephone;
    private String total_expense;
    private String desc;
    private String access_token;
    private String refresh_token;
    private String common_id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTotal_expense() {
        return total_expense;
    }

    public void setTotal_expense(String total_expense) {
        this.total_expense = total_expense;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getCommon_id() {
        return common_id;
    }

    public void setCommon_id(String common_id) {
        this.common_id = common_id;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender='" + gender + '\'' +
                ", icon_url='" + icon_url + '\'' +
                ", telephone='" + telephone + '\'' +
                ", total_expense='" + total_expense + '\'' +
                ", desc='" + desc + '\'' +
                ", id=" + id +
                '}';
    }
}
