package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.dao.UserDao;
import com.librarySystem.Demo.entity.History;
import com.librarySystem.Demo.entity.User;
import com.librarySystem.Demo.service.HistoryService;
import com.librarySystem.Demo.service.SeatService;
import com.librarySystem.Demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/history")
public class HistoryController
{
    @Autowired
    HistoryService historyService;

    @Autowired
    SeatService seatService;

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @GetMapping("/getAll")
    @ResponseBody
    public Object getAll()
    {
        return historyService.getAllUserHistory();
    }

    @RequestMapping("/getAllByUserId")
    @ResponseBody
    public Object getAllByUserId(@RequestBody Map<String, String> userid)
    {
        return historyService.getAllByUserId(userid.get("userid"));
    }

    @GetMapping("/signIn")
    @ResponseBody
    public Object signIn(HttpServletRequest request)
    {
        Date date = new Date();
        User user = (User) request.getSession().getAttribute("user");
        user = userDao.getCurInfo(user.getId());
        return historyService.signIn(user.getId(), user.getSeatId(), user.getReservetime(), date);
    }

    @GetMapping("/signOut")
    @ResponseBody
    public Object signOut(HttpServletRequest request)
    {
        Date date = new Date();
        User user = (User) request.getSession().getAttribute("user");
        user = userDao.getCurInfo(user.getId());
        return seatService.signOut(user, date);
    }

    @GetMapping("/stop")
    @ResponseBody
    public Object stopSeat(HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");
        user = userDao.getCurInfo(user.getId());
        return userService.updateState(user,"stop");
    }

    @GetMapping("/back")
    @ResponseBody
    public Object backSeat(HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");
        user = userDao.getCurInfo(user.getId());
        return userService.updateState(user,"back");
    }
}
