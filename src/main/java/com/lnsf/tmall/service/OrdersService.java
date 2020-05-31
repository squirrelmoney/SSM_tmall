package com.lnsf.tmall.service;
import com.lnsf.tmall.pojo.Orders;
import com.lnsf.tmall.pojo.Page;

import java.util.List;

public interface OrdersService {
    List<Orders> allOrders();
    Orders selectOrdersById(Integer id);
    int  updateOrdersitemById(Orders orders);
    int  deleteOrdersById(Integer id);
    int insert(Orders orders);
    List<Orders> selectByuid(Integer uid);
    List<Orders> showByPage(Integer start, Page page);
}
