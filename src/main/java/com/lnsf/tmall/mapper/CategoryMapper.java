package com.lnsf.tmall.mapper;

import com.lnsf.tmall.pojo.Category;
import com.lnsf.tmall.pojo.CategoryExample;
import java.util.List;

import com.lnsf.tmall.pojo.Page;
import org.apache.ibatis.annotations.Param;

public interface CategoryMapper {
    long countByExample(CategoryExample example);

    int deleteByExample(CategoryExample example);

    int deleteByPrimaryKey(Integer cid);

    int insert(Category record);

    int insertSelective(Category record);

    List<Category> selectByExample(CategoryExample example);

    Category selectByPrimaryKey(Integer cid);

    int updateByExampleSelective(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByExample(@Param("record") Category record, @Param("example") CategoryExample example);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    Category selectCategoryByname(String name);

    List<Category> page(@Param("page")Page page);

    Integer count();
}