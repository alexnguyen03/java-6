package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.model.Review;
import com.poly.repository.ReviewDAO;
import com.poly.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewDAO dao;

    @Override
    public Page<Review> findByNameProduct(String ten, Pageable pageable) {
        return dao.findByNameProduct(ten, pageable);
    }

    @Override
    public Page<Review> findByRating(Integer sao, Pageable pageable) {
       return dao.findByRating(sao, pageable);
    }

    @Override
    public Page<Review> findByNameAcount(String ten, Pageable pageable) {
        return dao.findByNameAcount(ten, pageable);
    }

    @Override
    public List<Review> findByProductId(int id) {
        return dao.findByProductId(id);
    }
    
}
