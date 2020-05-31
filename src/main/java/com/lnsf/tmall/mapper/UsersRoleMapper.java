package com.lnsf.tmall.mapper;

import com.lnsf.tmall.pojo.UsersRole;
import com.lnsf.tmall.pojo.UsersRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UsersRoleMapper {
    long countByExample(UsersRoleExample example);

    int deleteByExample(UsersRoleExample example);

    int insert(UsersRole record);

    int insertSelective(UsersRole record);

    List<UsersRole> selectByExample(UsersRoleExample example);

    int updateByExampleSelective(@Param("record") UsersRole record, @Param("example") UsersRoleExample example);

    int updateByExample(@Param("record") UsersRole record, @Param("example") UsersRoleExample example);
}