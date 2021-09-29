package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.entity.testUser;
import com.librarySystem.Demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    @ResponseBody
    public Map<String,String> login(@RequestBody Map<String,String> info,HttpServletRequest request){
        Map<String,String> log = new HashMap<>();
        String error="false";
        String account = info.get("account");
        String password = info.get("password");
        System.out.println(account+"\t"+password);
        testUser tu = (testUser) userService.login(account,password);
        if (tu!=null){
            System.out.println(tu.getSname()+"登陆成功");
            request.getSession().setAttribute("user",tu);
            log.put("username",tu.getSname());
        }
        else {
            System.out.println("登陆失败");
            log.put("username","error");
            error="true";
        }
        log.put("error",error);
        return log;
    }

//    @RequestMapping("/login")
//    public ModelAndView login(@RequestBody Map<String,String> user, ModelAndView mv, HttpServletRequest request, Model model){
//        System.out.println(user);
//        testUser login = (testUser) userService.login(user.get("account"),user.get("password"));
//        System.out.println(login);
//        if (login!=null){
//            request.getSession().setAttribute("login",login);
//            System.out.println("成功！！");
//            mv.setViewName("main");
//        }else{
//            System.out.println("失败！！");
//            mv.setViewName("index");
//        }
//        return mv;
//    }

    //管理员登录
    @PostMapping(value = "/adminlogin")
    @ResponseBody
    public Map<String,String> adminlogin(@RequestBody Map<String,String> user,HttpServletRequest request) {

        Map<String,String> log = new HashMap<>();
        String error="false";
        String account="";
        String password="";
        account = user.get("account");
        password = user.get("password");
        System.out.println("尝试登陆管理员账号:"+account);
        System.out.println("尝试的密码:"+password);
        testUser tu = (testUser) userService.login(account,password);
        if (tu!=null){
            System.out.println("管理员 "+tu.getSname()+" 登陆成功");
            request.getSession().setAttribute("user",tu);
            log.put("username",tu.getSname());
        }
        else {
            System.out.println("登陆失败");
            error="true";
            log.put("username","error");
        }
        log.put("error",error);
        return log;
    }

}
