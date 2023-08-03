package com.poly.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.query.Param;

import com.poly.model.Order;

public interface OrderService {
    Double getTotalRevenue();

    List<Order> findByStatus(String status);

    Long countProductsSoldByDate(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    
}
