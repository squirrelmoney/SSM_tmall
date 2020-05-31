package com.lnsf.tmall.service.impl;


import com.lnsf.tmall.mapper.PropertyMapper;
import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.pojo.Property;
import com.lnsf.tmall.service.PropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;
    @Override
    public List<Property> allProperty() {
        return propertyMapper.selectByExample(null);
    }

    @Override
    public Property selectPropertyById(Integer id) {
        return propertyMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePropertyitemById(Property Property) {
        return propertyMapper.updateByPrimaryKeySelective(Property);
    }

    @Override
    public int deletePropertyById(Integer id) {
        return propertyMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Property property) {
        return propertyMapper.insertSelective(property);
    }

    @Override
    public List<Property> selectBycid(Integer cid) {
        return propertyMapper.selectBycid(cid);
    }

    @Override
    public List<Property> showByPage(Integer start, Page page, Integer cid) {
        int totalCount =propertyMapper.count(cid);
        page.setTotalNumber(totalCount);
        page.setCurrentPage(start);
        List<Property> Propertys=propertyMapper.page(page,cid);
        return Propertys;
    }
}

