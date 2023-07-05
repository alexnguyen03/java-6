package com.poly.controller;

import java.io.File;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.poly.model.Account;
import com.poly.repository.AccountDAO;
import com.poly.service.EmailServiceImpl;
import com.poly.service.ParamService;
import com.poly.service.SessionService;
import com.poly.utils.EmailDetail;
import com.poly.utils.Generator;
import jakarta.validation.Valid;

@Controller
@RequestMapping("account")
public class SignupController {
    @Autowired
    SessionService sessionService;
    @Autowired
    EmailServiceImpl emailServiceImpl;
    @Autowired
    AccountDAO accountDAO;
    @Autowired
    ParamService paramService;

    @GetMapping("signup")
    public String getFormSignin(Account account) {
        return "/account/signup";
    }

    @PostMapping("signup")
    public String sendCode(@Valid Account account, BindingResult rs, @RequestParam("avt") MultipartFile file,
            Model model) {
        String confirmPass = paramService.getString("confirmPass", "");
        System.out.println(confirmPass);
        if (rs.hasErrors() || file.isEmpty() || !confirmPass.equals(account.getPassword())) {
            model.addAttribute("msg", "Vui lòng nhập đầy đủ thông tin để tạo tài khoản !");
            if (file.isEmpty()) {
                model.addAttribute("img_msg", "Vui lòng chọn ảnh đại diện !");
            }
            if (!confirmPass.equals(account.getPassword())) {
                model.addAttribute("confirmPassMsg", "Xác nhận mật khẩu không đúng, vui lòng thử lại !");
            }
            model.addAttribute("confirmPass", confirmPass);
            // model.addAttribute("alertType", "danger");
            return "account/signup";
        } else {
            // Account existAccount = accountDAO.findById(account.getUsername()).get();
            // if (existAccount != null) {
            // model.addAttribute("msg", "Tên đăng nhập đã tồn tại, vui lòng chọn tên khác
            // !");
            // return "account/signup";
            // }
            File newFile = paramService.save(file, "/img/user-management");
            account.setPhoto(newFile.getName());
            sessionService.set("signupMail", account.getEmail());
            sessionService.set("signupUser", account);
            String code = Generator.getCode();
            sessionService.set("code", code);
            EmailDetail details = new EmailDetail();
            details.setRecipient(account.getEmail());
            details.setSubject("Xác nhận tài khoản thành viên 3MEMS ");
            details.setMsgBody(
                    "Vui lòng nhập mã xác nhận bên dưới để hoàn thành tạo tài khoản\nMã xác nhận của bạn là " + code);
            String status = emailServiceImpl.sendSimpleMail(details);
            System.out.println(status);
            return "/account/verify";
        }
    }
}
