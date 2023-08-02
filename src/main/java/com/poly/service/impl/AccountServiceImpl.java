package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.model.Account;
import com.poly.model.Category;
import com.poly.repository.AccountDAO;
import com.poly.repository.CategoryDAO;
import com.poly.service.AccountService;
import com.poly.service.CategoryService;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	AccountDAO accountDAO;

	@Override
	public Account findById(String id) {
		return accountDAO.findById(id).get();
	}

	@Override
	public Account update(Account account) {
		return accountDAO.save(account);
	}


}
