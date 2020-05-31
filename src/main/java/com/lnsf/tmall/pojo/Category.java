package com.lnsf.tmall.pojo;

import java.util.List;

public class Category {
    private Integer cid;

    private String name;

    private String cpiturepath;


    /**
     * 在前端导航栏里，点击对应的分类右侧会显示推荐商品列表
     */
    List<List<Product>> productsList;

    List<Product> Products;

    public List<List<Product>> getProductsList() {
        return productsList;
    }

    public void setProductsList(List<List<Product>> productsList) {
        this.productsList = productsList;
    }

    public List<Product> getProducts() {
        return Products;
    }

    public void setProducts(List<Product> products) {
        Products = products;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getCpiturepath() {
        return cpiturepath;
    }

    public void setCpiturepath(String cpiturepath) {
        this.cpiturepath = cpiturepath == null ? null : cpiturepath.trim();
    }


}