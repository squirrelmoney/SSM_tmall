package com.lnsf.tmall.service;

import com.lnsf.tmall.pojo.Points;

import java.util.List;

public interface PointsService {
    List<Points> allPoints();
    Points selectPointsById(Integer id);
    int  updatePointsitemById(Points Points);
    int  deletePointsById(Integer id);
     int insert(Points points);
}
