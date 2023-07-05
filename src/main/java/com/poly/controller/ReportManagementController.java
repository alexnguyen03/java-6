package com.poly.controller;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.poly.model.Category;
import com.poly.model.ReportByCategory;
import com.poly.model.ReportByProduct;
import com.poly.model.ReportByUser;
import com.poly.model.ReportTop10;
import com.poly.repository.CategoryDAO;
import com.poly.repository.OrderDAO;
import com.poly.repository.OrderDetailDAO;
import com.poly.service.ParamService;

@Controller
@RequestMapping("/admin")
public class ReportManagementController {
    @Autowired
    OrderDetailDAO orderDetailDAO;
    @Autowired
    OrderDAO orderDAO;
    @Autowired
    CategoryDAO categoryDAO;
    @Autowired
    ParamService paramService;

    @GetMapping("/report/report-top-ten")
    public String reportTop10View(Model model) {
        model.addAttribute("reports", orderDetailDAO.getTopProduct());
        model.addAttribute("categories", categoryDAO.findAll());
        model.addAttribute("reportPage", "top");
        model.addAttribute("title", "BÁO CÁO - THỐNG KÊ");
        model.addAttribute("isPageActive", "statistic");
        return "/admin/report-top-ten";
    }

    @PostMapping("/report/report-top-ten")
    public String reportTop10Search(Model model) {
        String searchKey = paramService.getString("searchKey", "");
        List<ReportTop10> reports = null;
        if (!searchKey.isBlank()) {
            Date searchVal = null;
            LocalDate localDate = null;
            int day = 0;
            int month = 0;
            int year = 0;
            if (searchKey.equals("date")) {
                searchVal = paramService.getDate("searchVal", "yyyy-MM-dd");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                day = localDate.getDayOfMonth();
                month = localDate.getMonthValue();
                year = localDate.getYear();
                reports = orderDetailDAO.getTopProductByDate(day, month, year);
                model.addAttribute("searchVal", day + " tháng " + month + " năm " + year);
                model.addAttribute("searchKey", searchKey);
            } else if (searchKey.equals("week")) {
                String searchValString = paramService.getString("searchVal", "");
                String weekStr = searchValString.substring(6, searchValString.length());
                String yearStr = searchValString.substring(0, 4);
                reports = orderDetailDAO.getTopProductByWeek(Integer.parseInt(weekStr), Integer.parseInt(yearStr));
                model.addAttribute("searchVal", " thứ " + weekStr + " của  " + " năm " + yearStr);
                model.addAttribute("searchKey", searchKey);
            } else if (searchKey.equals("month")) {
                searchVal = paramService.getDate("searchVal", "yyyy-MM");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                month = localDate.getMonthValue();
                year = localDate.getYear();
                reports = orderDetailDAO.getTopProductByMonth(month, year);
                model.addAttribute("searchKey", searchKey);
                model.addAttribute("searchVal", month + " năm " + year);
            } else if (searchKey.equals("year")) {
                searchVal = paramService.getDate("searchVal", "yyyy");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                year = localDate.getYear();
                System.out.println(year + "year");
                reports = orderDetailDAO.getTopProductByYear(year);
                model.addAttribute("searchVal", year);
                model.addAttribute("searchKey", "year");
            }
            model.addAttribute("reports", reports);
        } else {
            model.addAttribute("msg", "Vui lòng chọn tiêu chí thống kê");
            model.addAttribute("reports", orderDetailDAO.getTopProduct());

        }
        model.addAttribute("title", "BÁO CÁO - THỐNG KÊ");
        model.addAttribute("reportPage", "top");
        model.addAttribute("categories", categoryDAO.findAll());
        model.addAttribute("isPageActive", "statistic");
        return "/admin/report-top-ten";
    }

    @GetMapping("/report/report-top-ten/chart")
    @ResponseBody
    public List<ReportTop10> getReportTop10(Model model) {
        return orderDetailDAO.getTopProduct();
    }

    @GetMapping("/report/report-by-category")
    public String reportByCategoryView(Model model) {
        model.addAttribute("reports", orderDetailDAO.getReportByCategories());
        model.addAttribute("isPageActive", "statistic");
        model.addAttribute("reportPage", "category");
        model.addAttribute("title", "BÁO CÁO - THỐNG KÊ");
        return "/admin/report-by-category";
    }

    @PostMapping("/report/report-by-category")
    public String reportByCategorySearch(Model model) {
        String searchKey = paramService.getString("searchKey", "");
        List<ReportByCategory> reports = null;
        if (!searchKey.isBlank()) {
            Date searchVal = null;
            LocalDate localDate = null;
            int day = 0;
            int month = 0;
            int year = 0;
            if (searchKey.equals("date")) {
                searchVal = paramService.getDate("searchVal", "yyyy-MM-dd");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                day = localDate.getDayOfMonth();
                month = localDate.getMonthValue();
                year = localDate.getYear();
                reports = orderDetailDAO.getReportByCategoriesByDate(day, month, year);
                model.addAttribute("searchVal", day + " tháng " + month + " năm " + year);
                model.addAttribute("searchKey", searchKey);
            } else if (searchKey.equals("week")) {
                String searchValString = paramService.getString("searchVal", "");
                String weekStr = searchValString.substring(6, searchValString.length());
                String yearStr = searchValString.substring(0, 4);
                reports = orderDetailDAO.getReportByCategoriesByWeek(Integer.parseInt(weekStr),
                        Integer.parseInt(yearStr));
                // Integer.parseInt(yearStr));
                model.addAttribute("searchVal", " thứ " + weekStr + " của  " + " năm " + yearStr);
                model.addAttribute("searchKey", searchKey);
            } else if (searchKey.equals("month")) {
                searchVal = paramService.getDate("searchVal", "yyyy-MM");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                month = localDate.getMonthValue();
                year = localDate.getYear();
                reports = orderDetailDAO.getReportByCategoriesByMonth(month, year);
                model.addAttribute("searchKey", searchKey);
                model.addAttribute("searchVal", month + " năm " + year);
            } else if (searchKey.equals("year")) {
                searchVal = paramService.getDate("searchVal", "yyyy");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                year = localDate.getYear();
                System.out.println(year + "year");
                reports = orderDetailDAO.getReportByCategoriesByYear(year);
                model.addAttribute("searchVal", year);
                model.addAttribute("searchKey", "year");
            }
            model.addAttribute("reports", reports);
        } else {
            model.addAttribute("msg", "Vui lòng chọn tiêu chí thống kê");
            model.addAttribute("reports", orderDetailDAO.getReportByCategories());
        }
        model.addAttribute("isPageActive", "statistic");
        model.addAttribute("reportPage", "category");
        model.addAttribute("title", "BÁO CÁO - THỐNG KÊ");
        return "/admin/report-by-category";
    }

    @GetMapping("/report/report-by-product")
    public String reportByProductView(Model model) {
        model.addAttribute("reports", orderDetailDAO.getReportByProducts());
        model.addAttribute("isPageActive", "statistic");
        model.addAttribute("title", "BÁO CÁO - THỐNG KÊ");
        model.addAttribute("reportPage", "product");
        return "/admin/report-by-product";
    }

    @PostMapping("/report/report-by-product")
    public String reportByProductSearch(Model model) {
        String searchKey = paramService.getString("searchKey", "");
        List<ReportByProduct> reports = null;
        if (!searchKey.isBlank()) {
            Date searchVal = null;
            LocalDate localDate = null;
            int day = 0;
            int month = 0;
            int year = 0;
            if (searchKey.equals("date")) {
                searchVal = paramService.getDate("searchVal", "yyyy-MM-dd");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                day = localDate.getDayOfMonth();
                month = localDate.getMonthValue();
                year = localDate.getYear();
                reports = orderDetailDAO.getReportByProductsByDate(day, month, year);
                model.addAttribute("searchVal", day + " tháng " + month + " năm " + year);
                model.addAttribute("searchKey", searchKey);
            } else if (searchKey.equals("week")) {
                String searchValString = paramService.getString("searchVal", "");
                String weekStr = searchValString.substring(6, searchValString.length());
                String yearStr = searchValString.substring(0, 4);
                reports = orderDetailDAO.getReportByProductsByWeek(Integer.parseInt(weekStr),
                        Integer.parseInt(yearStr));
                model.addAttribute("searchVal", " thứ " + weekStr + " của  " + " năm " + yearStr);
                model.addAttribute("searchKey", searchKey);
            } else if (searchKey.equals("month")) {
                searchVal = paramService.getDate("searchVal", "yyyy-MM");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                month = localDate.getMonthValue();
                year = localDate.getYear();
                reports = orderDetailDAO.getReportByProductsByMonth(month, year);
                model.addAttribute("searchKey", searchKey);
                model.addAttribute("searchVal", month + " năm " + year);
            } else if (searchKey.equals("year")) {
                searchVal = paramService.getDate("searchVal", "yyyy");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                year = localDate.getYear();
                System.out.println(year + "year");
                reports = orderDetailDAO.getReportByProductsByYear(year);
                model.addAttribute("searchVal", year);
                model.addAttribute("searchKey", "year");
            }
            model.addAttribute("reports", reports);
        } else {
            model.addAttribute("msg", "Vui lòng chọn tiêu chí thống kê");
            model.addAttribute("reports", orderDetailDAO.getReportByProducts());
        }
        model.addAttribute("isPageActive", "statistic");
        model.addAttribute("title", "BÁO CÁO - THỐNG KÊ");
        model.addAttribute("reportPage", "product");
        return "/admin/report-by-product";
    }

    @GetMapping("/report/report-by-user")
    public String reportByUserView(Model model) {
        model.addAttribute("reports", orderDetailDAO.getReportByUsers());
        model.addAttribute("reportPage", "user");
        model.addAttribute("isPageActive", "statistic");
        model.addAttribute("title", "BÁO CÁO - THỐNG KÊ");
        return "/admin/report-by-user";
    }

    @PostMapping("/report/report-by-user")
    public String reportByUserSearch(Model model) {
        String searchKey = paramService.getString("searchKey", "");
        List<ReportByUser> reports = null;
        if (!searchKey.isBlank()) {
            Date searchVal = null;
            LocalDate localDate = null;
            int day = 0;
            int month = 0;
            int year = 0;
            if (searchKey.equals("date")) {
                searchVal = paramService.getDate("searchVal", "yyyy-MM-dd");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                day = localDate.getDayOfMonth();
                month = localDate.getMonthValue();
                year = localDate.getYear();
                reports = orderDetailDAO.getReportByUsersByDate(day, month, year);
                model.addAttribute("searchVal", day + " tháng " + month + " năm " + year);
                model.addAttribute("searchKey", searchKey);
            } else if (searchKey.equals("week")) {
                String searchValString = paramService.getString("searchVal", "");
                String weekStr = searchValString.substring(6, searchValString.length());
                String yearStr = searchValString.substring(0, 4);
                reports = orderDetailDAO.getReportByUsersByWeek(Integer.parseInt(weekStr), Integer.parseInt(yearStr));
                model.addAttribute("searchVal", " thứ " + weekStr + " của  " + " năm " + yearStr);
                model.addAttribute("searchKey", searchKey);
            } else if (searchKey.equals("month")) {
                searchVal = paramService.getDate("searchVal", "yyyy-MM");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                month = localDate.getMonthValue();
                year = localDate.getYear();
                reports = orderDetailDAO.getReportByUsersByMonth(month, year);
                model.addAttribute("searchKey", searchKey);
                model.addAttribute("searchVal", month + " năm " + year);
            } else if (searchKey.equals("year")) {
                searchVal = paramService.getDate("searchVal", "yyyy");
                localDate = searchVal.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                year = localDate.getYear();
                System.out.println(year + "year");
                reports = orderDetailDAO.getReportByUsersByYear(year);
                model.addAttribute("searchVal", year);
                model.addAttribute("searchKey", "year");
            }
            model.addAttribute("title", "BÁO CÁO - THỐNG KÊ");
        } else {
            model.addAttribute("reports", orderDetailDAO.getReportByUsers());
            model.addAttribute("msg", "Vui lòng chọn tiêu chí thống kê");
        }
        model.addAttribute("reportPage", "user");
        model.addAttribute("reports", reports);
        model.addAttribute("categories", categoryDAO.findAll());
        model.addAttribute("isPageActive", "statistic");
        return "/admin/report-by-user";
    }

    @GetMapping("report")
    public String getReportView(Model model) {
        // Date date =
        // Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        // System.out.println(orderDAO.getTurnoverByDay(new Date()));
        model.addAttribute("reportByMonth", orderDAO.getTurnoverByMonth(new Date()));
        model.addAttribute("reportByYear", orderDAO.getTurnoverByYear(new Date()));
        model.addAttribute("reportByDate", orderDAO.getTurnoverByDay(new Date()));
        model.addAttribute("reportPage", "total");
        model.addAttribute("isPageActive", "statistic");
        model.addAttribute("title", "BÁO CÁO - THỐNG KÊ");
        return "/admin/report";
    }
}
