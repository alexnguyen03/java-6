package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.poly.service.SessionService;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
@SessionAttributes("previousUrl")
public class GlobalControllerAdvice {
	@Autowired
	SessionService session;

	@Autowired
	HttpServletRequest request;

	@ModelAttribute("totalCart")
	public Integer getTotalCart() {
		Integer totalCart = (Integer) session.get("totalCart");
		return totalCart != null ? totalCart : 0;
	}

	@ModelAttribute("account")
	public Boolean getIsLogin() {
		return session.get("account") != null ? true : false;
	}

	public String getPreviousUrlPath() {
		String previousUrl = request.getRequestURL().toString();
		session.set("previousUrl", previousUrl);
		return previousUrl;
	}
}