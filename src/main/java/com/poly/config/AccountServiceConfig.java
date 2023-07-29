package com.poly.config;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.poly.model.Account;
import com.poly.service.AccountService;

import jakarta.servlet.http.HttpSession;

public class AccountServiceConfig implements UserDetailsService {

	@Autowired
	private AccountService accountService;

	@Autowired
	HttpSession session;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountService.findById(username);
		if (account == null) {
			throw new UsernameNotFoundException("Không tìm thấy username");
		}
		Map<String, Object> authentication = new HashMap<>();
		authentication.put("user", account);
		byte[] token = (username + ":" + account.getPassword()).getBytes();
		authentication.put("token", "Basic " + Base64.getEncoder().encodeToString(token));
		session.setAttribute("authentication", authentication);
		return new User(account.getUsername(), account.getPassword(), account.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList()));
	}
}
