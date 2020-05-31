package com.lnsf.tmall.service.impl;
import com.lnsf.tmall.mapper.OrdersMapper;
import com.lnsf.tmall.pojo.Orders;
import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> allOrders() {
        return ordersMapper.selectByExample(null);
    }

    @Override
    public Orders selectOrdersById(Integer id) {
        return ordersMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateOrdersitemById(Orders orders) {
        return ordersMapper.updateByPrimaryKeySelective(orders);
    }

    @Override
    public int deleteOrdersById(Integer id) {
        return ordersMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Orders orders) {
        return ordersMapper.insertSelective(orders);
    }

    @Override
    public List<Orders> selectByuid(Integer uid) {
        return ordersMapper.selectByuid(uid);
    }

    @Override
    public List<Orders> showByPage(Integer start, Page page) {
        Integer totalCount =ordersMapper.count();

        page.setTotalNumber(totalCount);
        page.setCurrentPage(start);
        List<Orders> orders=ordersMapper.page(page);
        return orders;
    }
}


