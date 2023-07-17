package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.poly.model.Account;
import com.poly.service.SessionService;

@ControllerAdvice
public class GlobalControllerAdvice {
	@Autowired
	SessionService session;

	@ModelAttribute("totalCart")
	public Integer getTotalCart() {
		Integer totalCart = (Integer) session.get("totalCart");
		return totalCart != null ? totalCart : 0;
	}

	@ModelAttribute("account")
	public Boolean getIsLogin() {
		return session.get("account") != null ? true : false;
	}
}