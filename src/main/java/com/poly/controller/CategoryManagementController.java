package com.poly.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.model.Category;
import com.poly.repository.CategoryDAO;
import com.poly.service.SessionService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin/category-manager")
public class CategoryManagementController {
	@Autowired
	CategoryDAO categoryDAO;

	@Autowired
	SessionService session;

	@GetMapping("")
	public String getCategoryManager(Model model, @RequestParam("p") Optional<Integer> p,
			@RequestParam("field") Optional<String> field, @RequestParam("eop") Optional<Integer> eop,
			@RequestParam("d") Optional<Boolean> direc) {
//		Set AdminState
		session.set("stateAdmin", "admin");

		// init category
		Category item = new Category();
		model.addAttribute("item", item);

//		Default value
		int defaultPage = 0;
		int defaultElementOfPage = 5;
		String defaultField = "name";

		// asending is default
		Pageable pageable = PageRequest.of(p.orElse(defaultPage), eop.orElse(defaultElementOfPage),
				Sort.by(field.orElse(defaultField)).ascending());

		if (direc.isPresent() && !direc.get().booleanValue()) {
			pageable = PageRequest.of(p.orElse(defaultPage), eop.orElse(defaultElementOfPage),
					Sort.by(field.orElse(defaultField)).descending());
		}

		Page<Category> page = categoryDAO.findAll(pageable);
		model.addAttribute("page", page);
		model.addAttribute("field", field.orElse(defaultField));
		model.addAttribute("eop", eop.orElse(defaultElementOfPage));
		model.addAttribute("p", p.orElse(defaultPage));
		model.addAttribute("d", direc.orElse(true));

		// Sidebar Active
		model.addAttribute("isPageActive", "category");
		model.addAttribute("title", "QUẢN LÝ DANH MỤC");

		return "admin/category";
	}

	@GetMapping("/edit/{id}")
	public String editCategory(@ModelAttribute("category") Category category, @PathVariable("id") String id,
			Model model) {
		category = categoryDAO.findById(id).get();
		model.addAttribute("category", category);
		return "/admin/category-update";
	}

	@PostMapping("/update")
	public String updateCategory(@Valid @ModelAttribute("category") Category category, BindingResult binding,
			Model model, @RequestParam("id") String id, @RequestParam("name") String name) {
		Category item = categoryDAO.findById(id).get();
		item.setName(name);

		if (binding.hasErrors()) {
			return "/admin/category-update";
		}

		Category ct = categoryDAO.save(item);
		if (ct != null) {
			session.set("messageCategoryManager", "Cập nhật danh mục thành công");
		} else {
			session.set("messageCategoryManager", "Cập nhật danh mục thất bại");
		}

		return "redirect:/admin/category-manager";
	}

	@GetMapping("/add")
	public String addCategory(@ModelAttribute("category") Category category) {
		return "/admin/category-add";
	}

	@PostMapping("/create")
	public String create(@Valid @ModelAttribute("category") Category category, BindingResult binding, Model model) {

		if (binding.hasErrors()) {
			return "/admin/category-add";
		}

		Category categoryExisted = categoryDAO.findById(category.getId()).orElse(null);
		if (categoryExisted != null) {
			model.addAttribute("message", "Mã danh mục đã tồn tại vui lòng nhập vào mã danh mục khác");
			return "/admin/category-add";
		}

		session.set("messageCategoryManager", "Thêm danh mục thành công");
		categoryDAO.save(category);
		return "redirect:/admin/category-manager";
	}

	@RequestMapping("search-category")
	public String searchAndPageProduct(Model model, @RequestParam("keywords") Optional<String> kw,
			@RequestParam("p") Optional<Integer> p) {
		// Init Product
		Category item = new Category();
		model.addAttribute("item", item);

		String kwords = kw.orElse(session.get("keywords"));
		session.set("keywords", kwords);
		Pageable pageable = PageRequest.of(p.orElse(0), 5);
		Page<Category> page = categoryDAO.findByNameLike("%" + kwords + "%", pageable);
		model.addAttribute("page", page);
		return "admin/category";
	}
}
