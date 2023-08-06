package com.poly.service;

import com.poly.model.Coupon;

public interface CouponService {
	Coupon findByIdActivated(String username);
	Coupon findById(String couponCode);
}

