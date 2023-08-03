package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.poly.model.OrderDetail;
import com.poly.repository.OrderDetailDAO;
import com.poly.service.OrderDetailService;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailDAO orderDetailDAO;

    @Override
    public List<OrderDetail> getTop10OrderDetail(Pageable pageable) {
        return orderDetailDAO.getTop10OrderDetail(pageable);
    }
}