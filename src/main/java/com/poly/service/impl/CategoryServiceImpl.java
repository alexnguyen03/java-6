package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.poly.model.Category;
import com.poly.repository.CategoryDAO;
import com.poly.service.CategoriyService;

public class CategoryServiceImpl implements CategoriyService {
    @Autowired
    CategoryDAO dao;

    @Override
    public List<Category> findAll() {
        return dao.findAll();
    }

}
