package com.lnsf.tmall.controller;

import com.lnsf.tmall.pojo.*;
import com.lnsf.tmall.service.OrderitemService;
import com.lnsf.tmall.service.OrdersService;
import com.lnsf.tmall.service.ProductService;
import com.lnsf.tmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.List;

@Controller
public class Admin_Orders_Controller {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private OrderitemService orderitemService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @RequestMapping(value = "orders",method = RequestMethod.GET)
    public  String orders(Model m,Integer start){
        if (start == null)
            start=1;
        Page page=new Page();
        List<Orders> ordersList = ordersService.showByPage(start,page);
        for (Orders orders:ordersList){
            List<Orderitem> orderitems = orderitemService.selectByoid(orders.getOid());
            for (Orderitem orderitem:orderitems) {
                Product product = productService.selectProductById(orderitem.getPid());
                productService.FirstImage(product);
                orderitem.setProduct(product);
            }
            orders.setOrderitems(orderitems);
            User user = userService.selectUserById(orders.getUid());
            orders.setUser(user);
            orders.setNumberforone(orderitems.size());
        }
        m.addAttribute("page",page);
        m.addAttribute("ordersList",ordersList);
        return "admin/listOrder";
    }

    @RequestMapping(value = "deliver",method = RequestMethod.GET)
    public String deliver(Integer oid){
        Orders orders = ordersService.selectOrdersById(oid);
        Date date=new Date();
        System.out.println(date);
        orders.setDeliverydate(date);
        orders.setStatus("waitConfirm");
        int i = ordersService.updateOrdersitemById(orders);
        if (i!=0)
            return "redirect:/orders";
        else
            return null;
    }
}
