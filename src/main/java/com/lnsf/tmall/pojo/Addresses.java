package com.lnsf.tmall.pojo;

public class Addresses {
    private Integer aid;

    private Integer uid;

    private String address;

    private Integer mobile;

    private String recivename;

    private Integer status;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getMobile() {
        return mobile;
    }

    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getRecivename() {
        return recivename;
    }

    public void setRecivename(String recivename) {
        this.recivename = recivename == null ? null : recivename.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}