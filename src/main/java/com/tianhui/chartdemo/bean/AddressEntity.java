package com.tianhui.chartdemo.bean;

import com.tianhui.chartdemo.base.BasicEntity;

public class AddressEntity extends BasicEntity {
    private String user_id;
    private String receiver;
    private String contact_number;
    private String province;
    private String city;
    private String classify;
    private String detail_address;
    private String comment;
    private boolean is_default;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getClassify() {
        return classify;
    }

    public void setClassify(String classify) {
        this.classify = classify;
    }

    public String getDetail_address() {
        return detail_address;
    }

    public void setDetail_address(String detail_address) {
        this.detail_address = detail_address;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public boolean isIs_default() {
        return is_default;
    }

    public void setIs_default(boolean is_default) {
        this.is_default = is_default;
    }

    @Override
    public String toString() {
        return "AddressEntity{" +
                "user_id='" + user_id + '\'' +
                ", receiver='" + receiver + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", classify='" + classify + '\'' +
                ", detail_address='" + detail_address + '\'' +
                ", comment='" + comment + '\'' +
                ", is_default=" + is_default +
                ", id='" + id + '\'' +
                '}';
    }
}
