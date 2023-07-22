package com.poly.service;


import java.util.List;
import com.poly.model.Category;

public interface CategoryService {

	List<Category> getAllCategory();
	
	Category findById(String id);
	
	Category save(Category category);
}
