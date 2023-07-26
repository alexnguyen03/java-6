package com.poly.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Order implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Long id;
	String address;
	@Column(name = "user_phone")
	String phone;
	String status;
	String notes;
	@Column(name = "total_price")
	Double totalPrice;
	@Temporal(TemporalType.DATE)
	@Column(name = "create_date")
	Date createDate = new Date();
	@ManyToOne
	@JoinColumn(name = "username")
	Account account;
	@OneToMany(mappedBy = "order")
	List<OrderDetail> orderDetails;
	@ManyToOne
	@JoinColumn(name = "coupon_id")
	Coupon coupon;

	@Override
	public String toString() {
		return "Order [id=" + id + "]";
	}

}