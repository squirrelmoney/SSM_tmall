package com.lnsf.tmall.mapper;

import com.lnsf.tmall.pojo.Addresses;
import com.lnsf.tmall.pojo.AddressesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AddressesMapper {
    long countByExample(AddressesExample example);

    int deleteByExample(AddressesExample example);

    int deleteByPrimaryKey(Integer aid);

    int insert(Addresses record);

    int insertSelective(Addresses record);

    List<Addresses> selectByExample(AddressesExample example);

    Addresses selectByPrimaryKey(Integer aid);

    int updateByExampleSelective(@Param("record") Addresses record, @Param("example") AddressesExample example);

    int updateByExample(@Param("record") Addresses record, @Param("example") AddressesExample example);

    int updateByPrimaryKeySelective(Addresses record);

    int updateByPrimaryKey(Addresses record);
}