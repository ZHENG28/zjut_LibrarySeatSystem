package com.librarySystem.Demo.oldServlet.controller.seat;

import com.alibaba.fastjson.JSONObject;
import dao.SeatDAO;
import entity.Desk;
import entity.Student;
import util.Const;
import util.DateUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class BookSeatServlet extends HttpServlet
{
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String campus = request.getParameter("campus");
        Integer floors = Integer.parseInt(request.getParameter("floor"));
        Integer deskno = Integer.parseInt(request.getParameter("deskno"));
        // desk内容已更新
        Desk desk = SeatDAO.findByDeskno(campus, floors, deskno);

        String nowdate = DateUtil.format(new Date(), Const.DATE_FORMAT_ALL);
        String sno = request.getParameter("sno");
        // 获取到原来的stu信息
        Student stu = SeatDAO.findByStuId(sno);

        response.setContentType("json/application;charset=utf-8");
        if (SeatDAO.bookSeat(sno, nowdate, campus, floors, deskno)) {
            // book successful
            JSONObject json = new JSONObject();
            // 更新stu信息中的desk
            stu.desk = desk;
            // 传递desk最新内容
            json.put("deskInfo", desk.getDeskInfo());

            // 这个判断语句里的一定是已选座且选座成功的
            stu.setState(1);
            // 此处放置的状态未更新（×）-->更新状态
            json.put("userState", stu.getState());

            // 放到session中，使得页面刷新后仍可获得最新数据
            request.getSession(false).setAttribute("user", stu);
            // 传递json
            response.getWriter().println(json);
        }
    }
}
