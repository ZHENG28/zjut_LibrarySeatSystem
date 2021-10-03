package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.entity.Admin;
import com.librarySystem.Demo.entity.User;
import com.librarySystem.Demo.service.AdminService;
import com.librarySystem.Demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class SystemController
{
    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @PostMapping(value = "/user/login")
    @ResponseBody
    public Map<String, Object> userLogin(@RequestBody Map<String, String> info, HttpServletRequest request)
    {
        Map<String, Object> log = new HashMap<>();
        boolean error = false;

        String identity = info.get("identity");
        String account = info.get("account");
        String password = info.get("password");

        User user = userService.login(identity, account, password);
        if (user != null) {
            request.getSession().setAttribute("user", user);
        } else {
            log.put("errMsg", userService.findByIdentity(identity, account) ? "该账号不存在，请重新选择身份" : "账号或密码错误，请重新填写");
            error = true;
        }

        log.put("error", error);
        return log;
    }

    //管理员登录
    @PostMapping(value = "/admin/login")
    @ResponseBody
    public Map<String, Object> adminLogin(@RequestBody Map<String, String> info, HttpServletRequest request)
    {
        Map<String, Object> log = new HashMap<>();
        boolean error = false;

        String account = info.get("account");
        String password = info.get("password");

        Admin admin = adminService.login(account, password);
        if (admin != null) {
            request.getSession().setAttribute("admin", admin);
        } else {
            log.put("errMsg", "账号或密码错误，请重新填写");
            error = true;
        }

        log.put("error", error);
        return log;
    }

    //用户退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        //session失效
        session.removeAttribute("user");
        return "index";
    }

//    @PostMapping("/registerUser")
//    @ResponseBody
//    public Map<String, String> register(@RequestBody Map<String, String> user)
//    {
//
//        String sno = user.get("sno");
//        String sname = user.get("sname");
//        String password = user.get("password");
//        String campus = user.get("campus");
//        Map<String, String> info = new HashMap<>();
//
//        String error = "false";
//
//        if (systemService.register(sno, sname, password, campus)) {
//            info.put("info", "注册成功");
//        } else {
//            info.put("info", "注册失败");
//            error = "true";
//        }
//        info.put("error", error);
//        return info;
//    }
}
