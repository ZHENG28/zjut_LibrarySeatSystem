package com.librarySystem.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainViewController
{
    // 用户登陆页面、默认页面
    @GetMapping("/")
    public String index()
    {
        return "index";
    }

    // 前台页面
    @GetMapping("/foreend")
    public String main()
    {
        return "foreend";
    }

    //后台登录页
    @GetMapping("/adminLogin")
    public String admin()
    {
        return "adminLogin";
    }

    //后台页面
    @GetMapping("/backend")
    public String backend()
    {
        return "backend";
    }
}
