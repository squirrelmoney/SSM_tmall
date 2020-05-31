package com.lnsf.tmall.service.impl;


import com.lnsf.tmall.mapper.ProductMapper;
import com.lnsf.tmall.mapper.ProductimageMapper;
import com.lnsf.tmall.pojo.Category;
import com.lnsf.tmall.pojo.Product;
import com.lnsf.tmall.pojo.Productimage;
import com.lnsf.tmall.service.ProductService;
import com.lnsf.tmall.service.ProductimageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductimageServiceImpl implements ProductimageService {
    @Autowired
    private ProductimageMapper productimageMapper;

    @Override
    public List<Productimage> allProductimage() {
        return productimageMapper.selectByExample(null);
    }

    @Override
    public Productimage selectProductimageById(Integer id) {
        return productimageMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateProductimageitemById(Productimage Productimage) {
        return productimageMapper.updateByPrimaryKeySelective(Productimage);
    }

    @Override
    public int deleteProductimageById(Integer id) {
        return productimageMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Productimage productimage) {
        return productimageMapper.insertSelective(productimage);
    }

    @Override
    public List<Productimage> selectByPid(Integer pid) {
        return productimageMapper.selectByPid(pid);
    }

    @Override
    public List<Productimage> imgtype(String type,Integer pid) {
        List<Productimage> productimages = productimageMapper.selectByPid(pid);
        List<Productimage> list=new ArrayList<Productimage>();
        for (Productimage productimage:productimages) {
            if (productimage.getType().equals(type)){
                list.add(productimage);
            }
        }
        return  list;
    }


}


