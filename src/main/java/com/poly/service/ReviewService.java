package com.poly.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.model.Review;

public interface ReviewService {
	Page<Review> findByNameProduct(String ten, Pageable pageable);
	
	Page<Review> findByRating(Integer sao, Pageable pageable);
	
	Page<Review> findByNameAcount(String ten, Pageable pageable);
	
	List<Review> findByProductId(int id);
}
