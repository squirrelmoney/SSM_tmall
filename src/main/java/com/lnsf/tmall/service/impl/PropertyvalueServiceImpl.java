package com.lnsf.tmall.service.impl;


import com.lnsf.tmall.mapper.PropertyvalueMapper;
import com.lnsf.tmall.pojo.Propertyvalue;
import com.lnsf.tmall.service.PropertyvalueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyvalueServiceImpl implements PropertyvalueService {
    @Autowired
    private PropertyvalueMapper propertyvalueMapper;
    @Override
    public List<Propertyvalue> allPropertyvalue() {
        return propertyvalueMapper.selectByExample(null);
    }

    @Override
    public Propertyvalue selectPropertyvalueById(Integer id) {
        return propertyvalueMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePropertyvalueitemById(Propertyvalue Propertyvalue) {
        return propertyvalueMapper.updateByPrimaryKeySelective(Propertyvalue);
    }

    @Override
    public int deletePropertyvalueById(Integer id) {
        return propertyvalueMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Propertyvalue propertyvalue) {
        return propertyvalueMapper.insertSelective(propertyvalue);
    }

    @Override
    public List<Propertyvalue> selectBypid(Integer pid) {
        return propertyvalueMapper.selectBypid(pid);
    }
}

