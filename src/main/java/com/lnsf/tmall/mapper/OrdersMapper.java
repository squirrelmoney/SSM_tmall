package com.lnsf.tmall.mapper;

import com.lnsf.tmall.pojo.Orders;
import com.lnsf.tmall.pojo.OrdersExample;
import java.util.List;

import com.lnsf.tmall.pojo.Page;
import org.apache.ibatis.annotations.Param;

public interface OrdersMapper {
    long countByExample(OrdersExample example);

    int deleteByExample(OrdersExample example);

    int deleteByPrimaryKey(Integer oid);

    int insert(Orders record);

    int insertSelective(Orders record);

    List<Orders> selectByExample(OrdersExample example);

    Orders selectByPrimaryKey(Integer oid);

    int updateByExampleSelective(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByExample(@Param("record") Orders record, @Param("example") OrdersExample example);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<Orders> selectByuid(Integer uid);

    List<Orders> page(@Param("page") Page page);

    Integer count();
}