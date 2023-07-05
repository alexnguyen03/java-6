package com.poly.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.poly.model.Account;
import com.poly.model.Order;
import com.poly.model.OrderDetail;
import com.poly.model.Product;
import com.poly.repository.OrderDAO;
import com.poly.repository.OrderDetailDAO;
import com.poly.repository.ProductDAO;
import com.poly.service.EmailServiceImpl;
import com.poly.service.ParamService;
import com.poly.service.SessionService;
import com.poly.utils.EmailDetail;

@Controller
@RequestMapping("/admin")
public class OrderManagementController {
    @Autowired
    OrderDetailDAO orderDetailDAO;
    @Autowired
    ProductDAO productDAO;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    EmailServiceImpl emailServiceImpl;
    @Autowired
    SessionService sessionService;
    @Autowired
    ParamService paramService;

    @GetMapping("order")
    public String orderManagementView(Model model,
            @RequestParam("eop") Optional<Integer> eop,
            @RequestParam("p") Optional<Integer> p, @RequestParam("d") Optional<Boolean> direc) {
        int defaultPage = 0;
        int defaultElementOfPage = 5;
        List<Order> filterByStatus = null;
        // get param status
        String status = paramService.getString("status", "");
        Pageable pageable = PageRequest.of(p.orElse(defaultPage), eop.orElse(defaultElementOfPage));
        System.out.println(status.isBlank());
        Page<Order> page = orderDAO.findOrderActive(pageable);
        if (!status.isBlank()) {
            model.addAttribute("status", status);
            if (status.equals("All")) {
                page = orderDAO.findOrderActive(pageable);
                model.addAttribute("isFilterByStatusEmpty", true);
            } else {
                filterByStatus = orderDAO.findByStatus(status);
                if (filterByStatus.size() > 0) {
                    model.addAttribute("filterByStatus", filterByStatus);
                    model.addAttribute("isFilterByStatusEmpty", false);
                } else {
                    model.addAttribute("isFilterByStatusEmpty", true);
                }
            }
        }
        // List<Order> orders = orderDAO.findAll();
        // ! dsl
        // List<Order> orders = orderDAO.findOrderActive();
        // get canceled orders
        List<Order> orderCanceleds = orderDAO.findByStatus("H");
        String searchKey = paramService.getString("searchKey", "");
        if (!searchKey.isBlank()) {
            Date searchVal = paramService.getDate("searchVal", "yyyy-MM");
            LocalDate localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int day = localDate.getDayOfMonth();
            int month = localDate.getMonthValue();
            int year = localDate.getYear();
            if (searchKey.equals("date")) {
                page = orderDAO.findAllByCreateddateDate(day, month, year, pageable);
            }
            if (searchKey.equals("month")) {
                page = orderDAO.findAllByCreateddateMonth(month, year, pageable);
            }
            model.addAttribute("page", page);
            model.addAttribute("searchVal", searchVal);
            model.addAttribute("searchKey", searchKey);
        }
        System.out.println(page.getSize());
        // System.out.println(page.getSize());
        // System.out.println(filterByStatus.size());
        model.addAttribute("eop", eop.orElse(defaultElementOfPage));
        model.addAttribute("p", p.orElse(defaultPage));
        model.addAttribute("title", "QUẢN LÝ ĐƠN HÀNG");
        model.addAttribute("pageActive", "order");
        model.addAttribute("page", page);
        model.addAttribute("orderCanceleds", orderCanceleds);
        model.addAttribute("isPageActive", "order");
        if (sessionService.get("isUpdated") != null) {
            model.addAttribute("isUpdated", true);
            sessionService.remove("isUpdated");
        }
        System.out.println(page.getSize());
        return "/admin/order";
    }

    @PostMapping("order")
    public String updateStatus(@RequestParam("id") Long id, @RequestParam("status") String status,
            @RequestParam("notes") Optional<String> notes) {
        System.out.println(id + " " + status);
        Order order = orderDAO.findById(id).get();
        if (status.equals("H")) {
            order.setNotes(notes.get());
            // gui mail sau khi huy don hang
            Account account = (Account) sessionService.get("account");
            EmailDetail details = new EmailDetail();
            details.setRecipient(account.getEmail());
            details.setSubject("3MEMS - Thông báo hủy đơn hàng");
            details.setMsgBody(
                    "Thông tin đơn hàng của bạn\n"
                            + "Mã đơn hàng: " + id +
                            "\nTổng tiền đơn hàng: " + order.getTotalPrice() + " đ" +
                            "\nNgày đặt hàng: " + order.getCreateDate() +
                            "\nLý do đơn hàng bị hủy: " + notes +
                            "\n3MEMS thành thật xin lỗi quý khách vì đơn hàng đã bị hủy.\nMong quý khách luôn tin tưởng và ủng hộ 3MEMS trong thời gian sắp tới ! ");
            String sts = emailServiceImpl.sendSimpleMail(details);
            List<OrderDetail> ls = order.getOrderDetails();

            for (OrderDetail orderDetail : ls) {
                Product prod = orderDetail.getProduct();

                System.out.println("oldProd" + orderDetail.getProduct().getQuantity());
                System.out.println("orderDetail " + orderDetail.getQuantity());
                prod.setQuantity(prod.getQuantity() + orderDetail.getQuantity());
                System.out.println("new Prod " + prod.getQuantity());
                productDAO.save(prod);
            }
        }
        order.setStatus(status);
        orderDAO.save(order);
        sessionService.set("isUpdated", true);
        return "redirect:/admin/order";
    }

    @GetMapping("/order/cancel")
    public String orderCancelView(Model model) {
        model.addAttribute("isCancel", true);
        return "/admin/order";
    }

    // ModelAttribute
    @ModelAttribute("listStatus")
    public Map<String, String> listStatus() {
        Map<String, String> map = new HashMap<>();
        map.put("C", "Đang chờ");
        map.put("XL", "Đang xử lý");
        map.put("G", "Đang giao");
        map.put("DG", "Đã giao");
        map.put("H", "Hủy");
        return map;
    }
}
