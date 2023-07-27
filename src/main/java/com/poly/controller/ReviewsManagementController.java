package com.poly.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poly.model.Account;
import com.poly.model.Coupon;
import com.poly.model.Product;
import com.poly.model.Review;
import com.poly.repository.AccountDAO;
import com.poly.repository.ProductDAO;
import com.poly.repository.ReviewDAO;
import com.poly.service.EmailServiceImpl;
import com.poly.service.ParamService;
import com.poly.service.SessionService;
import com.poly.utils.EmailDetail;

@Controller
@RequestMapping("/admin/review")
public class ReviewsManagementController {
	@Autowired
	ReviewDAO dao;
	@Autowired
	ParamService paramService;
	@Autowired
	AccountDAO acdao;
	@Autowired
	ProductDAO productDao;
	@Autowired
	EmailServiceImpl emailServiceImpl;
	@Autowired
	SessionService sessionService;

	@GetMapping("")
	public String index(Model model, @RequestParam("p") Optional<Integer> p, @RequestParam("eop") Optional<Integer> eop,
			@RequestParam("field") Optional<String> field, @RequestParam("d") Optional<Boolean> direc) {
		int defaultPage = 0;
		int defaultElementOfPage = 3;
		String defaultField = "dateReview";
		String keyword = paramService.getString("keyword", " ");
		String search = paramService.getString("search", " ");
		model.addAttribute("isPageActive", "review");

		// asending is default
		Pageable pageable = PageRequest.of(p.orElse(defaultPage), eop.orElse(defaultElementOfPage),
				Sort.by(field.orElse(defaultField)).ascending());

		if (direc.isPresent() && !direc.get().booleanValue()) {
			pageable = PageRequest.of(p.orElse(defaultPage), eop.orElse(defaultElementOfPage),
					Sort.by(field.orElse(defaultField)).descending());
		}

		Page<Review> reviews = dao.findAll(pageable);
		if (search.equals("nameSP")) {
			if (keyword.equals("")) {
				model.addAttribute("success", "Vui lòng nhập dữ liệu trước khi tìm !!! ");
				model.addAttribute("isNameSP", true);
				return "/admin/reviews";
			}
			try {
				Integer.parseInt(keyword);
				model.addAttribute("success", "Tên sản phẩm phải là ký tự !!! ");
				model.addAttribute("isNameSP", true);
				return "/admin/reviews";
			} catch (Exception e) {
			}
			Date date = null;
			try {
				date = paramService.getDate(keyword, "yyyy-MM-dd");
				model.addAttribute("success", "Tên sản phẩm phải là ký tự !!! ");
				model.addAttribute("isNameSP", true);
			} catch (Exception e) {
			}
			reviews = dao.findByNameProduct("%" + keyword + "%", pageable);
			if (reviews.getTotalPages() > 0) {
				model.addAttribute("reviews", reviews);
				model.addAttribute("success", "Đã tìm thấy tên sản phẩm có chứa : " + keyword);
				model.addAttribute("isNameSP", true);
			} else {
				model.addAttribute("success", "Không tìm thấy sản phẩm có tên " + keyword);
			}
		} else if (search.equals("countS")) {
			if (keyword.equals("")) {
				model.addAttribute("success", "Vui lòng nhập dữ liệu trước khi tìm !!! ");
				model.addAttribute("iscountS", true);
				return "/admin/reviews";
			}
			try {
				Integer.parseInt(keyword);
			} catch (Exception e) {
				model.addAttribute("success", "Số sao phải là số nguyên !!! ");
				model.addAttribute("iscountS", true);
				return "/admin/reviews";
			}
			Date date = null;
			try {
				date = paramService.getDate(keyword, "yyyy-MM-dd");
			} catch (Exception e) {
				model.addAttribute("success", "Số sao phải là số nguyên !!! ");
				model.addAttribute("iscountS", true);
			}
			reviews = dao.findByRating(Integer.parseInt(keyword), pageable);
			if (reviews.getTotalPages() > 0) {
				model.addAttribute("reviews", reviews);
				model.addAttribute("success", "Đã tìm thấy đánh giá có " + keyword + " sao");
				model.addAttribute("iscountS", true);
			} else {
				model.addAttribute("success", "Không tìm thấy đánh giá có " + keyword + " sao");
			}
		} else if (search.equals("nameKH")) {
			if (keyword.equals("")) {
				model.addAttribute("success", "Vui lòng nhập dữ liệu trước khi tìm !!! ");
				model.addAttribute("isnameKH", true);
				return "/admin/reviews";
			}
			try {
				Integer.parseInt(keyword);
				model.addAttribute("success", "Tên khách hàng phải là ký tự !!! ");
				model.addAttribute("isnameKH", true);
				return "/admin/reviews";
			} catch (Exception e) {
			}
			Date date = null;
			try {
				date = paramService.getDate(keyword, "yyyy-MM-dd");
				model.addAttribute("success", "Tên khách hàng phải là ký tự !!! ");
				model.addAttribute("isnameKH", true);
			} catch (Exception e) {
			}
			reviews = dao.findByNameAcount("%" + keyword + "%", pageable);
			if (reviews.getTotalPages() > 0) {
				model.addAttribute("reviews", reviews);
				model.addAttribute("success", "Đã tìm thấy tên khách hàng có chứa : " + keyword);
				model.addAttribute("isnameKH", true);
			} else {
				model.addAttribute("success", "Không tìm thấy khách hàng có tên " + keyword);
			}
		}

		model.addAttribute("field", field.orElse(defaultField));
		model.addAttribute("eop", eop.orElse(defaultElementOfPage));
		model.addAttribute("p", p.orElse(defaultPage));
		model.addAttribute("d", direc.orElse(true));
		model.addAttribute("reviews", reviews);
		return "/admin/reviews";
	}

	@RequestMapping("search")
	public String searchCoupon(@RequestParam("search") String search, @RequestParam("keyword") String keyword) {
		return "forward:/admin/review";
	}

	@PostMapping("create")
	public String create(RedirectAttributes rdAtr) {
		int productId = Integer.parseInt(paramService.getString("productId", ""));
		int rating = Integer.parseInt(paramService.getString("rating", ""));
		String text = paramService.getString("textReview", "");

		Account ac = new Account();
		ac.setUsername("hoainam");
		ac.setPassword("123456");
		sessionService.set("account", ac);

		// lấy user từ session
		Account account_Session = sessionService.get("account");
		// kiểm tra có đăng nhập hay chưa
		// if (account_Session == null) {
		// 	sessionService.set("messageShop", "Đăng nhập để được đánh giá sản phẩm");
		// 	rdAtr.addAttribute("isMessageShop", true);
		// 	return "redirect:/account/login";
		// }
		Account account = acdao.findById(account_Session.getUsername()).get();
		Product product = productDao.findById(productId).get();
		Review review = new Review();
		review.setAccount(account);
		review.setProduct(product);
		review.setDateReview(new Date());
		review.setTextReview(text);
		review.setRating(rating);
		dao.save(review);
		return "redirect:/shop/product-detail?id=" + product.getId();
	}

	@GetMapping("delete/{id}")
	public String create(@PathVariable("id") Integer id) {
		Review review = dao.findById(id).get();
		EmailDetail details = new EmailDetail();
		details.setRecipient(review.getAccount().getEmail());
		details.setSubject("Quy phạm về quy tắc đánh giá của 3MEMS ");
		details.setMsgBody("Chào bạn " + review.getAccount().getFullname() + ", bạn đã đánh giá về sản phẩm "
				+ review.getProduct().getName() + " với nội dung " + review.getTextReview()
				+ ". Nội dung đó đã quy phạm về quy tắc của chúng tôi nên chúng tôi đã xóa đánh giá của bạn !!!. Cám ơn bạn.");
		String status = emailServiceImpl.sendSimpleMail(details);
		dao.deleteById(id);
		return "redirect:/admin/review";
	}

	@GetMapping("report")
	public String report() {
		return "/admin/chart";
	}
}
