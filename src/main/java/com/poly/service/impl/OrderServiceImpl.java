package com.poly.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.model.Order;
import com.poly.repository.OrderDAO;
import com.poly.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderDAO orderDAO;

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

}
