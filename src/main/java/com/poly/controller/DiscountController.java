package com.poly.controller;

import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poly.model.Coupon;
import com.poly.repository.CouponDAO;
import com.poly.service.SessionService;

@Controller
public class DiscountController {
	@Autowired
	CouponDAO couponDAO;
	@Autowired
	SessionService sessionService;

	@GetMapping("/apply-discount/{couponId}")
	@ResponseBody
	public String applyDiscount(@PathVariable("couponId") String couponId) {
		// Xử lý logic kiểm tra mã giảm giá hợp lệ và tính toán giá trị
		Coupon coupon = couponDAO.findByIdActivated(couponId);

		sessionService.set("coupon", coupon);
		double discount = coupon.getDiscountAmount();
		// Trả về kết quả dưới dạng JSON

		return "{ \"discount\": " + discount + " }";
	}
}
