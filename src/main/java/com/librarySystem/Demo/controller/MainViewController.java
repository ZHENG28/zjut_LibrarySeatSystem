package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.entity.testUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainViewController {


    @GetMapping("/")
    public String index(){
        return "index";
    }

    //登陆页面、默认页面
    @GetMapping("/index")
    public String homePage(){
        return "index";
    }

    //旧图书馆系统
    @GetMapping("/library")
    public String library(){
        return "library";
    }

    //新图书馆系统
    @GetMapping("/main")
    public String main(){
        return "main";
    }

    //后台登录页
    @GetMapping("/admin")
    public String admin(){
        return "login";
    }
    //后台页面
    @GetMapping("/backend")
    public String backend(){
        return "backend";
    }

    //用户退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session)
    {
        testUser tu = (testUser) session.getAttribute("user");
        System.out.println(tu.getSname()+"logout");
        tu = null;
        //session失效
        session.removeAttribute("user");
        return "index";
    }
}
