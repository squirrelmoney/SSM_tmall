package com.lnsf.tmall.mapper;

import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.pojo.Property;
import com.lnsf.tmall.pojo.PropertyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PropertyMapper {
    long countByExample(PropertyExample example);

    int deleteByExample(PropertyExample example);

    int deleteByPrimaryKey(Integer ptid);

    int insert(Property record);

    int insertSelective(Property record);

    List<Property> selectByExample(PropertyExample example);

    Property selectByPrimaryKey(Integer ptid);

    int updateByExampleSelective(@Param("record") Property record, @Param("example") PropertyExample example);

    int updateByExample(@Param("record") Property record, @Param("example") PropertyExample example);

    int updateByPrimaryKeySelective(Property record);

    int updateByPrimaryKey(Property record);

    List<Property> selectBycid(Integer cid);

    List<Property> page(@Param("page") Page page, @Param("cid") Integer cid);

    Integer count(Integer cid);
}