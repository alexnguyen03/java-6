package com.poly.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.model.Product;

public interface ProductService {
	Page<Product> getAllProducts(Pageable pageable);

	Product findById(Integer id);

	Product save(Product product);

	Page<Product> findByCategoryName(String name, Pageable pageable);

	Page<Product> findByPrice(double minPrice, double maxPrice, Pageable pageable);

	Page<Product> findByName(String name, Pageable pageable);

	Page<Product> findByPriceSortASC(Pageable pageable);

	Page<Product> findByPriceSortDESC(Pageable pageable);

	List<Product> findTop10BestSellingProducts();

	List<Product> findTop10ByOrderByCreateDateDesc();
}