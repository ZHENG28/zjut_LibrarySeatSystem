package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.entity.Seat;
import com.librarySystem.Demo.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

//        JSONObject json = new JSONObject();
//
//        String sno = request.getParameter("sno");
//        String campus = request.getParameter("campus");
//        Integer floors = Integer.parseInt(request.getParameter("floor"));
//
//        response.setContentType("json/application;charset=utf-8");
//        String seatLayout = SeatDAO.getLayout(campus, floors);
//        int selected = SeatDAO.findDeskno(sno);
//        JSONObject json = new JSONObject();
//        json.put("seatLayout", seatLayout);
//        json.put("selected", selected);

        return seatService.getSeatInfo();
    }
}
