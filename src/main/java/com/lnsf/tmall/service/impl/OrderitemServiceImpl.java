package com.lnsf.tmall.service.impl;

import com.lnsf.tmall.mapper.OrderitemMapper;
import com.lnsf.tmall.pojo.Orderitem;
import com.lnsf.tmall.service.OrderitemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderitemServiceImpl implements OrderitemService{
    @Autowired
    private OrderitemMapper orderitemMapper;
    @Override
    public List<Orderitem> allOrderitem() {
        return orderitemMapper.selectByExample(null);
    }

    @Override
    public Orderitem selectOrderitemById(Integer id) {
        return orderitemMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateOrderitemById(Orderitem orderitem) {
        return orderitemMapper.updateByPrimaryKeySelective(orderitem);
    }

    @Override
    public int deleteOrderitemById(Integer id) {
        return orderitemMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Orderitem orderitem) {
        return orderitemMapper.insertSelective(orderitem);
    }

    @Override
    public List<Orderitem> selectBypid(Integer pid) {
        return orderitemMapper.selectBypid(pid);
    }

    @Override
    public List<Orderitem> selectByuid(Integer uid) {
        return orderitemMapper.selectByuid(uid);
    }

    @Override
    public List<Orderitem> selectByoid(Integer oid) {
        return orderitemMapper.selectByoid(oid);
    }

    @Override
    public int countCart(Integer uid) {
        List<Orderitem> orderitems = orderitemMapper.selectByuid(uid);
        int count=0;
        for (Orderitem o:orderitems) {
            if (o.getOid()==null){
                count++;
            }
        }
        return count;
    }
}
