package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.entity.testUser;
import com.librarySystem.Demo.service.UserService;
import com.librarySystem.Demo.utils.QRCode;
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
        String errInfo="不存在该用户";//未来可能加入“用户不存在”或者“密码错误”的返回信息
        String account = info.get("account");
        String password = info.get("password");
        System.out.println(account+"\t"+password);
        testUser tu = (testUser) userService.login(account,password);
        if (tu!=null){
            System.out.println(tu.getSname()+"登陆成功");
            request.getSession().setAttribute("user",tu);
            QRCode.CreateQRCode(tu.getSname());
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

    //管理员登录
    @PostMapping(value = "/adminlogin")
    @ResponseBody
    public Map<String,String> adminlogin(@RequestBody Map<String,String> user,HttpServletRequest request) {

        Map<String,String> log = new HashMap<>();
        String error = "false";
        String errInfo ="不存在该用户";//未来可能加入“用户不存在”或者“密码错误”的返回信息
        String account = user.get("account");
        String password = user.get("password");
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
            log.put("username","login error");
        }
        log.put("error",error);
        return log;
    }

    @PostMapping("/registerUser")
    @ResponseBody
    public Map<String,String> register(@RequestBody Map<String,String> user){

        String sno = user.get("sno");
        String sname = user.get("sname");
        String password = user.get("password");
        String campus = user.get("campus");
        Map<String,String> info = new HashMap<>();

        String error = "false";

        if (userService.register(sno,sname,password,campus)){
            info.put("info","注册成功");
        }
        else {
            info.put("info","注册失败");
            error = "true";
        }
        info.put("error",error);
        return info;
    }

}
