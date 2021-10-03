package com.librarySystem.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/foreend")
public class ForeendViewController
{

    String v = "foreend/";

    @GetMapping("/homePage")
    public String console()
    {
        return v + "homePage";
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

    @GetMapping("/insertseats")
    public String insertseats()
    {
        return v + "insertseats";
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

    @GetMapping("/searchBook")
    public String searchbook()
    {
        return v + "searchBook";
    }

    @GetMapping("/seatInfo")
    public String seatinfo()
    {
        return v + "seatInfo";
    }

    @GetMapping("/seatView")
    public String seatview()
    {
        return v + "seat/seatView";
    }

    @GetMapping("/userInfo")
    public String userinfo()
    {
        return v + "userInfo";
    }

    @GetMapping("/qrcode")
    public String qrcode()
    {
        return v + "qrcode";
    }
}
