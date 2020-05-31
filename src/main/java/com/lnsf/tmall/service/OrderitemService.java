package com.lnsf.tmall.service;

import com.lnsf.tmall.pojo.Orderitem;

import java.util.List;

public interface OrderitemService {
    List<Orderitem> allOrderitem();
    Orderitem selectOrderitemById(Integer id);
    int  updateOrderitemById(Orderitem orderitem);
    int  deleteOrderitemById(Integer id);
    int insert(Orderitem orderitem);
    List<Orderitem> selectBypid(Integer pid);
    List<Orderitem> selectByuid(Integer uid);
    List<Orderitem> selectByoid(Integer oid);
    int countCart(Integer uid);
}
