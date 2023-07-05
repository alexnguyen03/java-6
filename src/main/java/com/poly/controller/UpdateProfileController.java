package com.poly.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.poly.model.Account;
import com.poly.repository.AccountDAO;
import com.poly.service.ParamService;
import com.poly.service.SessionService;

import jakarta.servlet.ServletContext;

@Controller
@RequestMapping("account")
public class UpdateProfileController {
	@Autowired
	AccountDAO dao;
	@Autowired
	ServletContext app;
	@Autowired
	SessionService sessionService;
	@Autowired
	ParamService paramService;

	@GetMapping("update-account")
	public String index(Model model, Account account) {
		Account account_session = sessionService.get("account");
		account = dao.findById(account_session.getUsername()).get();
		model.addAttribute("account", account);
		return "/account/update_account";
	}

	@PostMapping("update")
	public String update(Model model, @RequestParam("photo_file") MultipartFile img,
			@Validated @ModelAttribute("account") Account account, BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("success", "Vui lòng sửa các lỗi sau !!!");
		}else {
			if (img.isEmpty()) {
				dao.save(account);
				model.addAttribute("success", "Cập nhật tài khoản thành công");
			} else {
				paramService.save(img, "/img/user-management");
				account.setPhoto(img.getOriginalFilename());
				dao.save(account);
				model.addAttribute("success", "Cập nhật tài khoản thành công");
			}
		}
		return "/account/update_account";
	}

}
