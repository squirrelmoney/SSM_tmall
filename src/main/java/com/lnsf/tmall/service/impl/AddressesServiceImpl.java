package com.lnsf.tmall.service.impl;
import com.lnsf.tmall.mapper.AddressesMapper;
import com.lnsf.tmall.pojo.Addresses;
import com.lnsf.tmall.service.AddressesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service//获取当前类的对象,注解是为了IOC(依赖注入)，让spring来创建对象
public class AddressesServiceImpl implements AddressesService {

@Autowired//它可以对类成员变量、方法及构造函数进行标注，让 spring 完成 bean 自动装配的工作。
private AddressesMapper addressesMapper;

    @Override
    public List<Addresses> allAddresses() {
        return addressesMapper.selectByExample(null);
    }

    @Override
    public Addresses selectAddressById(Integer aid) {
        return addressesMapper.selectByPrimaryKey(aid);
    }

    @Override
    public int updateAddressById(Addresses addresses) {
        return addressesMapper.updateByPrimaryKeySelective(addresses);
    }

    @Override
    public int deleteAddressById(Integer aid) {
        return addressesMapper.deleteByPrimaryKey(aid);
    }

    @Override
    public int insert(Addresses addresses) {
        return addressesMapper.insertSelective(addresses);
    }
}
