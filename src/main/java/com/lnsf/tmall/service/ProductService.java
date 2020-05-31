package com.lnsf.tmall.service;

import com.lnsf.tmall.pojo.Category;
import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.pojo.Product;
import com.lnsf.tmall.pojo.Productimage;

import java.util.List;

public interface ProductService {
    List<Product> allProduct();
    Product selectProductById(Integer id);
    int  updateProductitemById(Product Product);
    int  deleteProductById(Integer id);
    int insert(Product product);
    List<Product> selectBycid(Integer cid);
    public void fillByRow(Category category, int productNumberEachRow);
    /*购物车的图片*/
    void FirstImage(Product product);
    void ProductFirstImage(List<Product> products);
    List<Productimage> ProductHotest(List<Product> product, int n);
    List<Product> selectByWords(String words);
    int saleCount(Product product);
    int reviewCount(Product product);
    List<Product> sortByreview(List<Product> products);
    List<Product> sortBydate(List<Product> products);
    List<Product> sortBysaleCount(List<Product> products);
    List<Product> sortBysaleprice(List<Product> products);
    List<Product> showByPage(Integer start, Page page);
}
