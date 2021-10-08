package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/backend")
public class BackendViewController
{
    String v = "backend/";

    // 主页
    @GetMapping("/homePage")
    public String consoleBack()
    {
        return v + "homePage";
    }

    // 座位信息
    @GetMapping("/seatInfo")
    public String seatInfo()
    {
        return v + "seatInfo";
    }

    @GetMapping("/insertSeats")
    public String insertSeats()
    {
        return v + "insertSeats";
    }

    // 用户信息
    @GetMapping("/userInfo")
    public String userInfo()
    {
        return v + "userInfo";
    }

    @GetMapping("/userHistory")
    public String userHistory()
    {
        return v + "userHistory";
    }

    @GetMapping("/form")
    public String form()
    {
        return v + "form";
    }

    @GetMapping("/guide")
    public String guide()
    {
        return v + "guide";
    }

    @GetMapping("/insertuser")
    public String insertuser()
    {
        return v + "insertuser";
    }

    @GetMapping("/operaterule")
    public String operaterule()
    {
        return v + "operaterule";
    }

    @GetMapping("/userinfomanage")
    public String userinfomanage()
    {
        return v + "userinfomanage";
    }

    @GetMapping("/users")
    public String users()
    {
        return v + "users";
    }

}
