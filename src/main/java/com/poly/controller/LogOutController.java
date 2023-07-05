package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.service.SessionService;

@Controller
@RequestMapping("/log-out")
public class LogOutController {
	@Autowired
	SessionService session;

	@RequestMapping("")
	public String logOut() {
		session.remove("username");
		session.remove("account");
		session.remove("stateAdmin");
		return "redirect:/";
	}
}
