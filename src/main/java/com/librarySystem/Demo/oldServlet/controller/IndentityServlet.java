package com.librarySystem.Demo.oldServlet.controller;

import entity.Student;
import entity.User;
import tool.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class IndentityServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        Integer gender = Integer.parseInt(request.getParameter("gender"));
        String campus = request.getParameter("campus");
        String birthday = request.getParameter("birthday");
        String sno = request.getParameter("sno");

        response.setContentType("text/html;charset=utf-8");
        if (UserDAO.modifyUser(gender, campus, birthday, sno) == 1) {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user instanceof Student) {
                Student stu = (Student) user;
                stu.setGender(gender);
                stu.setCampus(campus);
                stu.setBirthday(birthday);
                session.setAttribute("user", stu);
            }
            request.getRequestDispatcher("/main/library.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        doPost(req, resp);
    }
}
