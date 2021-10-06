package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.entity.Seat;
import com.librarySystem.Demo.entity.User;
import com.librarySystem.Demo.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/seat")
public class SeatController
{
    @Autowired
    SeatService seatService;

    @RequestMapping("/getInfo")
    @ResponseBody
    public List<Seat> getSeatInfo()
    {
        return seatService.getSeatInfo();
    }

    @RequestMapping("/reserve")
    @ResponseBody
    public Object reserve(Integer seatId, HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");
        // 日期格式注意下，还没格式化过
        Date time = new Date();
        System.out.println(time);

        return seatService.reserve(user.getId(), seatId, time);
    }
}
