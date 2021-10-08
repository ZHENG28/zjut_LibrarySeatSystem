package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.entity.Seat;
import com.librarySystem.Demo.entity.User;
import com.librarySystem.Demo.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @RequestMapping("/getSeatById")
    @ResponseBody
    public Seat getSeatById(Integer seatid)
    {
        return seatService.getSeatById(seatid);
    }

    @RequestMapping("/toFloor")
    public ModelAndView toFloor(String campus)
    {
        ModelAndView mv = new ModelAndView();
        int floorNum = "朝晖".equals(campus) || "莫干山".equals(campus) ? 3 : 5;
        mv.addObject("idleList", seatService.getIdleList(campus, floorNum));
        mv.addObject("stateList", seatService.getState(campus, floorNum));
        String str = "朝晖".equals(campus) ? "zh" : ("屏峰".equals(campus) ? "pf" : "mgs");
        mv.setViewName("foreend/seat/" + str + "FloorView");
        return mv;
    }

    @RequestMapping("/reserve")
    @ResponseBody
    public Object reserve(Integer seatId, HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");
        return seatService.reserve(user.getId(), seatId, new Date());
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object updateSeat(HttpServletRequest request)
    {
        User user = (User) request.getSession().getAttribute("user");
        return seatService.update(user.getId());
    }

}
