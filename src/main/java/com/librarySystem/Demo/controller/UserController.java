package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.entity.testUser;
import com.librarySystem.Demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    @ResponseBody
    public int login(@RequestBody Map<String,String> info){
        String account = info.get("account");
        String password = info.get("password");
        System.out.println(account+"\t"+password);
        testUser tu = (testUser) userService.login(account,password);
        if (tu!=null){
            System.out.println(tu.getSname());
            return 1;
        }
        else {
            System.out.println("不存在该用户");
            return 0;
        }
    }

}
