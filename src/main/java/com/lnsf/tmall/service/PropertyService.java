package com.lnsf.tmall.service;

import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.pojo.Property;

import java.util.List;

public interface PropertyService {
    List<Property> allProperty();
    Property selectPropertyById(Integer id);
    int  updatePropertyitemById(Property Property);
    int  deletePropertyById(Integer id);
    int insert(Property property);
    List<Property> selectBycid(Integer cid);

    List<Property> showByPage(Integer start, Page page, Integer cid);
}
