package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController
{
    @RequestMapping("/getBtnState")
    @ResponseBody
    public Object getBtnState(HttpServletRequest request)
    {
        Map<String, Object> info = new HashMap<>();
        User user = (User) request.getSession().getAttribute("user");
        int seatId = user.getSeatId();
        int state = user.getState();
        if (seatId == 0 && state == 0) { // 未选座
            info.put("signin", false);
            info.put("signout", false);
            info.put("stop", false);
        } else if (seatId != 0 && state != 0) { // 已选座
            switch (state) {
                case 1: // 已选座
//                    info.put("signin", false);
//                    info.put("signout", false);
//                    info.put("stop", false);
                    break;
                case 2: // 暂离
//                    info.put("signin", false);
//                    info.put("signout", false);
//                    info.put("stop", false);
                    break;
            }
        } else {
            throw new RuntimeException("seatId与state不相符合");
        }

        return info;
    }
}
