package com.librarySystem.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
}
