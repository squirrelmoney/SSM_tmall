package com.lnsf.tmall.service;

import com.lnsf.tmall.pojo.Category;
import com.lnsf.tmall.pojo.Product;
import com.lnsf.tmall.pojo.Productimage;

import java.util.List;

public interface ProductimageService {
    List<Productimage> allProductimage();
    Productimage selectProductimageById(Integer id);
    int  updateProductimageitemById(Productimage Productimage);
    int  deleteProductimageById(Integer id);
    int insert(Productimage productimage);
    List<Productimage> selectByPid(Integer pid);
    List<Productimage> imgtype(String type,Integer pid);
}
