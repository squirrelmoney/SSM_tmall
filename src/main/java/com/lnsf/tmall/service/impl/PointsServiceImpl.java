package com.lnsf.tmall.service.impl;

import com.lnsf.tmall.mapper.PointsMapper;
import com.lnsf.tmall.pojo.Points;
import com.lnsf.tmall.service.PointsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointsServiceImpl implements PointsService {
    @Autowired
    private PointsMapper pointsMapper;
    @Override
    public List<Points> allPoints() {
        return pointsMapper.selectByExample(null);
    }

    @Override
    public Points selectPointsById(Integer id) {
        return pointsMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updatePointsitemById(Points Points) {
        return pointsMapper.updateByPrimaryKeySelective(Points);
    }

    @Override
    public int deletePointsById(Integer id) {
        return pointsMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Points points) {
        return pointsMapper.insertSelective(points);
    }
}
