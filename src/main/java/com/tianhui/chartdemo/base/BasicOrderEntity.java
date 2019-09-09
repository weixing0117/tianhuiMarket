package com.tianhui.chartdemo.base;

public class BasicOrderEntity extends BasicEntity{
    private String desc;
    private String comment;
    private String contact;
    private String contact_number;
    private String total_cost;
    private Integer total_number;
    private String contact_address;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    @Override
    public String toString() {
        return "BasicOrderEntity{" +
                "desc='" + desc + '\'' +
                ", comment='" + comment + '\'' +
                ", contact='" + contact + '\'' +
                ", contact_number='" + contact_number + '\'' +
                ", total_cost='" + total_cost + '\'' +
                ", total_number=" + total_number +
                ", id='" + id + '\'' +
                '}';
    }
}
