package com.lnsf.tmall.mapper;

import com.lnsf.tmall.pojo.Page;
import com.lnsf.tmall.pojo.Product;
import com.lnsf.tmall.pojo.ProductExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductMapper {
    long countByExample(ProductExample example);

    int deleteByExample(ProductExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Product record);

    int insertSelective(Product record);

    List<Product> selectByExample(ProductExample example);

    Product selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByExample(@Param("record") Product record, @Param("example") ProductExample example);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectBycid(Integer cid);

    List<Product> selectByWords(String name);

    List<Product> page(@Param("page") Page page);

    Integer count();
}