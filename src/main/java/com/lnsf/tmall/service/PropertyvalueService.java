package com.lnsf.tmall.service;

import com.lnsf.tmall.pojo.Propertyvalue;

import java.util.List;

public interface PropertyvalueService {
    List<Propertyvalue> allPropertyvalue();
    Propertyvalue selectPropertyvalueById(Integer id);
    int  updatePropertyvalueitemById(Propertyvalue Propertyvalue);
    int deletePropertyvalueById(Integer id);
    int insert(Propertyvalue propertyvalue);
    List<Propertyvalue> selectBypid(Integer pid);
}
