package com.poly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Category;

public interface CategoryDAO extends JpaRepository<Category, String> {
	Page<Category> findByNameLike(String name, Pageable pageable);
}
