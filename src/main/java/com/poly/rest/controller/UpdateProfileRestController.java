package com.poly.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poly.model.Account;
import com.poly.service.AccountService;
import com.poly.service.SessionService;

import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/update-account")
public class UpdateProfileRestController {
	@Autowired
	AccountService accountService;
	@Autowired
	SessionService sessionService;
	
	@GetMapping()
	public Optional<Account> getOne() {
		return accountService.findById("hoainam");
	}
	
	@PutMapping("{id}")
	public Account update(@PathVariable("id") String id, @RequestBody Account account) {
		return accountService.update(account);
	}
	
}
