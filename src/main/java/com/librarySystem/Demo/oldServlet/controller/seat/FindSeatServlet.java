package com.librarySystem.Demo.oldServlet.controller.seat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.SeatDAO;
import entity.Desk;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FindSeatServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String sno = request.getParameter("sno");
        String campus = request.getParameter("campus");
        Integer floors = Integer.parseInt(request.getParameter("floor"));

        response.setContentType("json/application;charset=utf-8");
        String seatLayout = SeatDAO.getLayout(campus, floors);
        int selected = SeatDAO.findDeskno(sno);
        JSONObject json = new JSONObject();
        json.put("seatLayout", seatLayout);
        json.put("selected", selected);
        response.getWriter().println(json);
    }
}
