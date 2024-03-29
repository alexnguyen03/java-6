package com.poly.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Coupons")
public class Coupon implements Serializable {
    @Id
    @Column(name = "coupon_code")
    @NotBlank(message = "{NotBlank.coupon.couponCode}")
    @Size(min = 6, max = 6, message = "{Size.coupon.couponCode}")
    String couponCode;

    @Column(name = "discount_amount")
    @NotNull(message = "{NotNull.coupon.discountAmount")
    @Min(value = 0, message = "{Min.coupon.discountAmount}")
    @Max(value = 100, message = "{Max.coupon.discountAmount}")
    Double discountAmount;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "expiration_date")
    @NotNull(message = "{NotNull.coupon.expirationDate}")
    Date expirationDate;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "start_date")
    @NotNull(message = "{NotNull.coupon.startDate}")
    Date startDate;

    Boolean activated;

    @Column(name = "coupon_name")
    @NotBlank(message = "{NotBlank.coupon.couponName}")
    String couponName;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "created_date")
    Date createdDate = new Date();
    @OneToMany(mappedBy = "coupon")
    private List<Order> orders;
}
