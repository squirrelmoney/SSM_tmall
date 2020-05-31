package com.lnsf.tmall.service;

import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.pojo.User;

import java.util.List;

public interface UserService {
    List<User> allUser();
    User selectUserById(Integer id);
    int  updateUseritemById(User User);
    int  deleteUserById(Integer id);
    int insert(User user);
    User selectByName(String name);
    String setNameToAnonymous(String name);
    List<User> showByPage(Integer start, Page page);

}
