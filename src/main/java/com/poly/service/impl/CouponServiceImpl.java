package com.poly.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poly.model.Coupon;
import com.poly.repository.CouponDAO;
import com.poly.service.CouponService;

@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    CouponDAO couponDAO;

    @Override
    public List<Coupon> findAll() {
        return couponDAO.findAll();
    }

    @Override
    public Coupon findByIDActive(String id) {
        return couponDAO.findByIdActivated(id);
    }

    @Override
    public Coupon save(Coupon coupon) {
        return couponDAO.save(coupon);
    }
}
