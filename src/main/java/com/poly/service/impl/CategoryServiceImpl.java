package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.model.Category;
import com.poly.repository.CategoryDAO;
import com.poly.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	@Autowired
	CategoryDAO categoryDAO;

	@Override
	public List<Category> getAllCategory() {
		return categoryDAO.findAll();
	}

	@Override
	public Category findById(String id) {
		return categoryDAO.findById(id).get();
	}

	@Override
	public Category save(Category category) {
		return categoryDAO.save(category);
	}

}
