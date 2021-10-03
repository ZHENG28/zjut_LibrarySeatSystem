package com.librarySystem.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views")
public class SecondaryViewController {

    String v = "views/";

    @GetMapping("/console")
    public String console(){
        return v+"console";
    }

    @GetMapping("/form")
    public String form(){
        return v+"form";
    }

    @GetMapping("/guide")
    public String guide(){
        return v+"guide";
    }

    @GetMapping("/insertseats")
    public String insertseats(){
        return v+"insertseats";
    }

    @GetMapping("/insertuser")
    public String insertuser(){
        return v+"insertuser";
    }

    @GetMapping("/operaterule")
    public String operaterule(){
        return v+"operaterule";
    }

    @GetMapping("/searchbook")
    public String searchbook(){
        return v+"searchbook";
    }

    @GetMapping("/seatinfo")
    public String seatinfo(){
        return v+"seatinfo";
    }

    @GetMapping("/seatview")
    public  String  seatview(){
        return v+"seatview";
    }

    @GetMapping("/userinfo")
    public String userinfo(){
        return v+"userinfo";
    }

    @GetMapping("/userinfomanage")
    public String userinfomanage(){
        return v+"userinfomanage";
    }

    @GetMapping("/users")
    public String users(){
        return v+"users";
    }

    @GetMapping("/qrcode")
    public String qrcode(){
        return v+"qrcode";
    }
}
