package com.lnsf.tmall.service.impl;


import com.lnsf.tmall.mapper.UserMapper;
import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.pojo.User;
import com.lnsf.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<User> allUser() {
        return userMapper.selectByExample(null);
    }

    @Override
    public User selectUserById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateUseritemById(User User) {
        return userMapper.updateByPrimaryKeySelective(User);
    }

    @Override
    public int deleteUserById(Integer id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }

    @Override
    public String setNameToAnonymous(String name) {
        name.substring(1,name.length()-1).replaceAll(".","*");
        return name;
    }

    @Override
    public List<User> showByPage(Integer start, Page page) {
        Integer totalCount =userMapper.count();
        page.setTotalNumber(totalCount);
        page.setCurrentPage(start);
        List<User> users=userMapper.page(page);
        return users;
    }


}


