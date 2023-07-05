package com.poly.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import com.poly.model.Account;
import com.poly.model.Cart;
import com.poly.model.CartDetail;
import com.poly.model.Coupon;
import com.poly.model.Order;
import com.poly.model.OrderDetail;
import com.poly.repository.CartDAO;
import com.poly.repository.CartDetailDAO;
import com.poly.repository.OrderDAO;
import com.poly.repository.OrderDetailDAO;
import com.poly.repository.ProductDAO;
import com.poly.service.ParamService;
import com.poly.service.PaypalService;
import com.poly.service.SessionService;

@Controller
public class PaypalController {

	@Autowired
	PaypalService service;
	@Autowired
	SessionService sessionService;
	@Autowired
	OrderDAO orderDAO;
	@Autowired
	ParamService paramService;
	@Autowired
	CartDAO cartDAO;
	@Autowired
	CartDetailDAO cartDetailDAO;
	@Autowired
	ProductDAO productDAO;
	@Autowired
	OrderDetailDAO orderDetailDAO;

	public static final String SUCCESS_URL = "pay/success";
	public static final String CANCEL_URL = "pay/cancel";

	@PostMapping("/pay")
	public String payment() {
		double total = paramService.getDouble("total", 0);
		String phone = paramService.getString("sdt", "");
		String address = paramService.getString("dc", "");
		sessionService.set("phone", phone);
		sessionService.set("address", address);
		try {
			Payment payment = service.createPayment(total, "USD", "paypal", "sale", address,
					"http://localhost:8080/" + CANCEL_URL, "http://localhost:8080/" + SUCCESS_URL);
			for (Links link : payment.getLinks()) {
				if (link.getRel().equals("approval_url")) {
					return "redirect:" + link.getHref();
				}
			}

		} catch (PayPalRESTException e) {

			e.printStackTrace();
		}
		return "redirect:/";
	}

	@GetMapping(value = CANCEL_URL)
	public String cancelPay() {
		return "cancel";
	}

	@GetMapping(value = SUCCESS_URL)
	public String successPay(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId) {
		double toTal_Price = 0;
		Account account = sessionService.get("account");
		Cart cart = cartDAO.findByUserName(account.getUsername());
		// lấy product
		List<CartDetail> listCartDetail = cart.getCartDetails();
		for (CartDetail od : listCartDetail) {
			double toTal = od.getProduct().getPrice() * od.getQuantity();
			toTal_Price += toTal;
		}

		Coupon coupon = sessionService.get("coupon");
		String phone = sessionService.get("phone");
		String address = sessionService.get("address");

		double discountAmount = 0.0;
		if (coupon != null) {
			discountAmount = coupon.getDiscountAmount();
		}

		try {
			Payment payment = service.executePayment(paymentId, payerId);
			System.out.println(payment.toJSON());
			if (payment.getState().equals("approved")) {
				Order order = new Order();
				order.setCoupon(coupon);
				order.setAccount(account);
				order.setPhone(phone);
				order.setAddress(address);
				order.setTotalPrice(toTal_Price - (toTal_Price * (discountAmount / 100)));
				order.setStatus("C");

				orderDAO.save(order);
				sessionService.remove("coupon");

				for (CartDetail od : listCartDetail) {
					OrderDetail orderDetail = new OrderDetail();
					orderDetail.setOrder(order);
					orderDetail.setProduct(od.getProduct());
					orderDetail.setPrice(od.getProduct().getPrice());
					orderDetail.setQuantity(od.getQuantity());
					orderDetailDAO.save(orderDetail);
					// xóa sản phẩm của cartdetail
					int productId = od.getProduct().getId();
					cartDetailDAO.deleteByProductId(productId);
					// cập nhật lại số lượng của sản phẩm
					productDAO.updateQuantityProduct(od.getQuantity(), productId);
				}
				return "redirect:/shop/order-history";
			}
		} catch (PayPalRESTException e) {
			System.out.println(e.getMessage());
		}
		return "redirect:/shop/order-history";
	}

}