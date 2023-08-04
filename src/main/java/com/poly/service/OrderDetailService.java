package com.poly.service;

import com.poly.model.OrderDetail;

import java.util.List;

public interface OrderDetailService {
	List<OrderDetail> findByOrderId(Long id);
}
