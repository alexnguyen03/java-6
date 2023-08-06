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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
import com.poly.service.impl.OrderDetailImpl;
import com.poly.service.impl.OrderServiceImpl;
import com.poly.service.impl.ProductServiceImpl;
import com.poly.utils.EmailDetail;

@Controller
@CrossOrigin("*")
@RequestMapping("/admin")
public class OrderManagementController {
    @Autowired
    OrderDetailDAO orderDetailDAO;
    @Autowired
    OrderDetailImpl orderDetailService;
    @Autowired
    ProductServiceImpl productService;
    @Autowired
    OrderServiceImpl orderService;
    @Autowired
    EmailServiceImpl emailServiceImpl;
    @Autowired
    SessionService sessionService;
    @Autowired
    ParamService paramService;

    @GetMapping("orders")
    @ResponseBody
    public List<Order> getAll() {
        return orderService.findAll();
    }

    @GetMapping("orders/detail/{orderID}")
    @ResponseBody
    public List<OrderDetail> getAllOrderDetailByOrderID(@PathVariable("orderID") Long id) {
        return orderDetailService.findByOrderId(id);
    }

    @GetMapping("orders/users/{orderID}")
    @ResponseBody
    public Account getAllAccount(@PathVariable("orderID") Long id) {
        return orderService.findAllAccount(id);
    }

    @PutMapping("orders/{orderID}")
    public ResponseEntity<Object> updateOrder(@RequestBody Order order, @PathVariable("orderID") Long id) {

        return ResponseEntity.ok(orderService.save(order));
    }

    public String updateStatus(@RequestParam("id") Long id, @RequestParam("status") String status,
            @RequestParam("notes") Optional<String> notes) {
        System.out.println(id + " " + status);
        Order order = orderService.findById(id);
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
                productService.save(prod);
            }
        }
        order.setStatus(status);
        orderService.save(order);
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
