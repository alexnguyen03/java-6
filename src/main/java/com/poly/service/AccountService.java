package com.poly.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.poly.model.Account;

public interface AccountService {

	Account findById(String id);

	Account update(Account account);

}
