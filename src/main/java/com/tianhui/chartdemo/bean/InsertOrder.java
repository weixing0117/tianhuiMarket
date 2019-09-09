package com.tianhui.chartdemo.bean;

import java.util.List;

public class InsertOrder {
    private String address_id;
    private String comment;
    private String contact;
    private String contact_number;
    private String total_cost;
    private Integer total_number;
    private String contact_address;
    private List<InsertOrderBean> insertOrderBeanList;

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public List<InsertOrderBean> getInsertOrderBeanList() {
        return insertOrderBeanList;
    }

    public void setInsertOrderBeanList(List<InsertOrderBean> insertOrderBeanList) {
        this.insertOrderBeanList = insertOrderBeanList;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(String total_cost) {
        this.total_cost = total_cost;
    }

    public Integer getTotal_number() {
        return total_number;
    }

    public void setTotal_number(Integer total_number) {
        this.total_number = total_number;
    }

    public String getContact_address() {
        return contact_address;
    }

    public void setContact_address(String contact_address) {
        this.contact_address = contact_address;
    }
}
