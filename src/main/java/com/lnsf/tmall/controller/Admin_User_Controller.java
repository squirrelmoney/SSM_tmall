package com.lnsf.tmall.controller;

import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.pojo.User;
import com.lnsf.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class Admin_User_Controller {
    @Autowired
     private  UserService userService;

    @RequestMapping(value = "users",method = RequestMethod.GET)
    public String allUsers(Model m,Integer start){
        if (start == null)
            start=1;
        Page page=new Page();
        List<User> users = userService.showByPage(start,page);
        m.addAttribute("page",page);
        m.addAttribute("us",users);
        return "admin/listUser";
    }

}
