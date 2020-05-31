package com.lnsf.tmall.service.impl;


import com.lnsf.tmall.mapper.ReviewMapper;
import com.lnsf.tmall.pojo.Review;
import com.lnsf.tmall.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private ReviewMapper reviewMapper;

    @Override
    public List<Review> allReview() {
        return reviewMapper.selectByExample(null);
    }

    @Override
    public Review selectReviewById(Integer id) {
        return reviewMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateReviewitemById(Review Review) {


        return reviewMapper.updateByPrimaryKeySelective(Review);
    }

    @Override
    public int deleteReviewById(Integer id) {
        return reviewMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Review review) {
        return reviewMapper.insertSelective(review);
    }

    @Override
    public List<Review> selectBypid(Integer pid) {
        return reviewMapper.selectBypid(pid);
    }
}


