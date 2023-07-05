package com.poly.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.model.Account;

public interface AccountDAO extends JpaRepository<Account, String> {
    public Page<Account> findByFullnameLike(String keyword, Pageable page);

    // public List<String> findBy
}
