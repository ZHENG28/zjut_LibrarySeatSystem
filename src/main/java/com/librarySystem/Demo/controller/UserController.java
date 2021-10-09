package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.entity.History;
import com.librarySystem.Demo.entity.User;
import com.librarySystem.Demo.service.HistoryService;
import com.librarySystem.Demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    UserService userService;

    @RequestMapping("/getBtnState")
    @ResponseBody
    public Object getBtnState(HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");
        return userService.getBtnState(user.getId());
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public Object getAllUser()
    {
        return userService.getAllUser();
    }

    @RequestMapping("/getState")
    @ResponseBody
    public Object getState(String userid)
    {
        return userService.getState(userid);
    }

    @RequestMapping("/getLikeTag")
    @ResponseBody
    public Object getLikeTag(String userid)
    {
        return userService.getLikeTag(userid);
    }
}
