package com.poly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
public class ForgotPasswordController {
    @Autowired
    SessionService sessionService;
    @Autowired
    EmailServiceImpl emailServiceImpl;
    @Autowired
    AccountDAO accountDAO;

    @Autowired
    ParamService paramService;

    @GetMapping("forgotpassword")
    public String getFormForgotPassword(Account account) {
        return "/account/forgotpassword";
    }

    @PostMapping("forgotpassword")
    public String checkUser(Model model, Account account) {
        // mock account form session
        // sessionService.set("account", accountDAO.findById("Alex2").get());
        System.out.println(account.getUsername());
        Boolean existAcc = accountDAO.findById(account.getUsername()).isPresent();
        if (account.getUsername().isBlank() || account.getEmail().isBlank()) {
            model.addAttribute("username", account.getUsername());
            model.addAttribute("email", account.getEmail());
            if (!existAcc) {
                model.addAttribute("msg", "Tên đăng nhập không chính xác, vui lòng thử lại !");
                return "/account/forgotpassword";
            }
            model.addAttribute("msg", "Vui lòng nhập đầy đủ thông tin để tạo tài khoản !");
            return "/account/forgotpassword";
        } else if (!existAcc) {
            model.addAttribute("msg", "Tên đăng nhập không chính xác, vui lòng thử lại !");
            model.addAttribute("username", account.getUsername());
            return "/account/forgotpassword";
        } else {
            sessionService.set("email", account.getEmail());
            String code = Generator.getCode();
            sessionService.set("code-resetpass", code);
            System.out.println("code-resetpass" + code);
            EmailDetail details = new EmailDetail();
            details.setRecipient(account.getEmail());
            details.setSubject("Xác nhận đặt lại mật khẩu cho tài khoản thành viên 3MEMS ");
            details.setMsgBody(
                    "Vui lòng nhập mã xác nhận bên dưới để hoàn thành xác nhận tài khoản\nMã xác nhận của bạn là "
                            + code);
            String status = emailServiceImpl.sendSimpleMail(details);
            System.out.println(status);
            return "/account/token";
        }
    }

    @GetMapping("token")
    public String getFormToken() {

        return "/account/token";
    }

    @PostMapping("resetpassword")
    public String getFormResetpassword(@RequestParam("code") String code, Model model) {
        String codeResetpass = sessionService.get("code-resetpass");
        model.addAttribute("email", sessionService.get("email"));
        if (!code.equals(codeResetpass)) {
            model.addAttribute("msg", "Mã xác nhận không chính xác vui lòng kiểm tra lại !");
            return "/account/token";
        } else {
            return "/account/resetpassword";
        }
    }

    @PostMapping("updatePass")
    public String updatePass(Model model) {
        String newPass = paramService.getString("password", "");
        String confirmNewPass = paramService.getString("passwordConfirmation", "");
        if (!confirmNewPass.equals(newPass)) {
            model.addAttribute("msg", "Xác nhận mật khẩu không chính xác, vui lòng thử lại sau !");
            return "/account/resetpassword";
        } else if (newPass.length() < 6 || newPass.length() > 12) {

            model.addAttribute("msg", "Mật khẩu phải có độ dài từ 6 đến 12 ký tự !");
            return "/account/resetpassword";
        } else {
            Account account = sessionService.get("account");
            account.setPassword(newPass);
            accountDAO.save(account);
            System.out.println("reset pass successfully !");
            return "redirect:/account/login";
        }
    }
}
