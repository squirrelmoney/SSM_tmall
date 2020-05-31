package com.lnsf.tmall.service;

import com.lnsf.tmall.pojo.Review;

import java.util.List;

public interface ReviewService {
    List<Review> allReview();
    Review selectReviewById(Integer id);
    int  updateReviewitemById(Review Review);
    int  deleteReviewById(Integer id);
    int insert(Review review);
    List<Review> selectBypid(Integer pid);

}
