package com.lnsf.tmall.mapper;

import com.lnsf.tmall.pojo.Points;
import com.lnsf.tmall.pojo.PointsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PointsMapper {
    long countByExample(PointsExample example);

    int deleteByExample(PointsExample example);

    int deleteByPrimaryKey(Integer pid);

    int insert(Points record);

    int insertSelective(Points record);

    List<Points> selectByExample(PointsExample example);

    Points selectByPrimaryKey(Integer pid);

    int updateByExampleSelective(@Param("record") Points record, @Param("example") PointsExample example);

    int updateByExample(@Param("record") Points record, @Param("example") PointsExample example);

    int updateByPrimaryKeySelective(Points record);

    int updateByPrimaryKey(Points record);
}