package com.poly.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.poly.model.OrderDetail;

public interface OrderDetailService {
    List<OrderDetail> getTop10OrderDetail(Pageable pageable);
}
