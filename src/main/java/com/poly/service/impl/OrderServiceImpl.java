package com.poly.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.model.Account;
import com.poly.model.Order;
import com.poly.model.OrderDetail;
import com.poly.model.Payment;
import com.poly.repository.AccountDAO;
import com.poly.repository.OrderDAO;
import com.poly.repository.OrderDetailDAO;
import com.poly.service.OrderService;
import com.poly.service.SessionService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderDAO orderDAO;

	@Autowired
	AccountDAO accountDAO;

	@Autowired
	SessionService sessionService;

	@Autowired
	OrderDetailDAO orderDetailDAO;

	@Override
	public Order create(JsonNode orderData) {
		sessionService.set("orderData", orderData);

		ObjectMapper mapper = new ObjectMapper();
		Order order = mapper.convertValue(orderData, Order.class);
		if (order.getPayment().getIdPaymemt() == 2) {
			if (order.getCoupon().getCouponCode() != null) {
				orderDAO.save(order);

				TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
				};
				List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type).stream()
						.peek(d -> d.setOrder(order)).collect(Collectors.toList());
				orderDetailDAO.saveAll(details);
//				 response.sendRedirect("/order-history"); // Thay thế "/order-history" 
			} else {
				order.setCoupon(null);
				orderDAO.save(order);

				TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {
				};
				List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type).stream()
						.peek(d -> d.setOrder(order)).collect(Collectors.toList());
				orderDetailDAO.saveAll(details);
			}
		}
		return order;
	}
}
