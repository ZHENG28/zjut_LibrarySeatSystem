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
    HistoryService historyService;

    @Autowired
    UserService userService;

    @RequestMapping("/getBtnState")
    @ResponseBody
    public Object getBtnState(HttpServletRequest request)
    {
        Map<String, Object> info = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        int seatId = user.getSeatId();
        int state = user.getState();
        System.out.println(user);
        History history = historyService.getHistoryByUser(user);
        if (seatId == 0 && state == 0) { // 未选座
            info.put("signin", false);
            info.put("signout", false);
            info.put("stop", false);
            info.put("back", false);
        } else if (seatId != 0 && state != 0) { // 已选座
            switch (state) {
                case 1: // 已选座
                    if (history.getReachtime() != null) { // 已签到
                        info.put("signin", false);
                        info.put("signout", true);
                        info.put("stop", true);
                        info.put("back", false);
                    } else { // 未签到
                        info.put("signin", true);
                        info.put("signout", false);
                        info.put("stop", false);
                        info.put("back", false);
                    }
                    break;
                case 2: // 暂离
                    info.put("signin", false);
                    info.put("signout", true);
                    info.put("stop", false);
                    info.put("back", true);
                    break;
            }
        } else {
            throw new RuntimeException("seatId与state不相符合");
        }

        return info;
    }

    @RequestMapping("/getAllUser")
    @ResponseBody
    public Object getAllUser()
    {
        return userService.getAllUser();
    }
}
