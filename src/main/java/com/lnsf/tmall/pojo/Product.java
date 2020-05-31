package com.lnsf.tmall.pojo;

import java.util.Date;

public class Product {
    private Integer pid;

    private String name;

    private String subtitle;

    private Float originalprice;

    private Float promoteprice;

    private Integer stock;

    private Integer cid;

    private Date createdate;

    private Integer points;

    private Productimage productimage;

    private Integer salecount;

    private Integer reviewcount;

    public Integer getReviewcount() {
        return reviewcount;
    }

    public void setReviewcount(Integer reviewcount) {
        this.reviewcount = reviewcount;
    }

    public Integer getSalecount() {
        return salecount;
    }

    public void setSalecount(Integer salecount) {
        this.salecount = salecount;
    }

    public Productimage getProductimage() {
        return productimage;
    }

    public void setProductimage(Productimage productimage) {
        this.productimage = productimage;
    }

    @Override
    public String toString() {
        return "Product{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", originalprice=" + originalprice +
                ", promoteprice=" + promoteprice +
                ", stock=" + stock +
                ", cid=" + cid +
                ", createdate=" + createdate +
                ", points=" + points +
                ", ProductFirstImage=" + productimage +
                '}';
    }



    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public Float getOriginalprice() {
        return originalprice;
    }

    public void setOriginalprice(Float originalprice) {
        this.originalprice = originalprice;
    }

    public Float getPromoteprice() {
        return promoteprice;
    }

    public void setPromoteprice(Float promoteprice) {
        this.promoteprice = promoteprice;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}