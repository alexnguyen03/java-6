package com.poly.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poly.model.Order;
import com.poly.model.OrderDetail;
import com.poly.model.Product;
import com.poly.model.ReportByProduct;
import com.poly.repository.OrderDAO;
import com.poly.repository.OrderDetailDAO;
import com.poly.repository.ProductDAO;
import com.poly.service.SessionService;

@Controller
@RequestMapping("/admin")
public class AdminIndexController {
	@Autowired
	OrderDAO orderlDAO;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	OrderDetailDAO orderDetailDAO;

	@Autowired
	SessionService session;

	@RequestMapping("")
	public String index(Model model) {
		// Set AdminState
		session.set("stateAdmin", "admin");

		model.addAttribute("isPageActive", "index");
		model.addAttribute("orderXLCount", orderlDAO.findByStatus("C").size());

		// SELECT PRODUCT NEWEST TODAY
		Date start = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date end = Date.from(LocalDate.now().atTime(LocalTime.MAX).atZone(ZoneId.systemDefault()).toInstant());
		List<Product> products = productDAO.findByCreateDateBetween(start, end);
		model.addAttribute("productNewsetToday", products.size());

		// SELECT All REvenue
		Double totalRevenue = orderDAO.getTotalRevenue();
		if (totalRevenue == null) {
			totalRevenue = 0.0;
		}
		model.addAttribute("totalRevenue", totalRevenue);

		// SELECT Revenue from 1 month past

		// SELECT ALL SOLD PRODUCT IN DAY
		LocalDate today = LocalDate.now();
		Date startDateSold = Date.from(today.atStartOfDay(ZoneId.systemDefault()).toInstant());
		Date endDateSold = Date.from(today.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant());
		Long totalProductsSoldToday = orderDAO.countProductsSoldByDate(startDateSold, endDateSold);
		System.out.println(totalProductsSoldToday);
		model.addAttribute("totalProductsSoldToday", totalProductsSoldToday);

		List<OrderDetail> getPageTopTenOrderDetails = orderDetailDAO.getTop10OrderDetail(PageRequest.of(0, 10));
		model.addAttribute("getPageTopTenOrderDetails", getPageTopTenOrderDetails);

//		SELECT TOP 10 PRODUCT BEST SELLING
		List<Product> bestSellingProduct = productDAO.findTop10BestSellingProducts();
		model.addAttribute("bestSellingProduct", bestSellingProduct);

		return "/admin/index";
	}
}
