package com.poly.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.model.Account;

@Controller
@RequestMapping("account")
public class LogOutController {

	@RequestMapping("logout/success")
	public String logOutSuccess(Model model, Account account) {
		model.addAttribute("message", "You are logged out!");
		return "account/login";
	}
}
