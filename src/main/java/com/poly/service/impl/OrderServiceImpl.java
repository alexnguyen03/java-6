package com.poly.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.poly.model.Account;
import com.poly.repository.AccountDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.model.Order;
import com.poly.model.OrderDetail;
import com.poly.repository.OrderDAO;
import com.poly.repository.OrderDetailDAO;
import com.poly.service.OrderService;
import com.poly.service.SessionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    SessionService sessionService;

    @Autowired
    OrderDetailDAO orderDetailDAO;
    AccountDAO accountDAO;

    @Override
    public List<Order> findAll() {
        return orderDAO.findAll();
    }

    @Override
    public Order findById(Long id) {
        return orderDAO.findById(id).get();
    }

    @Override
    public List<Order> findAllCancel() {
        return findAllCancel();
    }

    @Override
    public Account findAllAccount(Long id) {
        return orderDAO.findOrderById(id);
    }

    @Override
    public List<Order> findByUsername(String username) {
        return orderDAO.findByAccountName(username);
    }

    @Override
    public List<Order> findAllCancelByUsername(String username) {
        return orderDAO.findAllByStatusNotLikeAndAccount_Username("H", username);
    }

    @Override
    public Order save(Order order) {
        return orderDAO.save(order);
    }

    @Override
    public Double getTotalRevenue() {
        return orderDAO.getTotalRevenue();
    }

    @Override
    public List<Order> findByStatus(String status) {
        return orderDAO.findByStatus(status);
    }

    @Override
    public Long countProductsSoldByDate(Date startDate, Date endDate) {
        return orderDAO.countProductsSoldByDate(startDate, endDate);
    }

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
                // response.sendRedirect("/order-history"); // Thay thế "/order-history"
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
