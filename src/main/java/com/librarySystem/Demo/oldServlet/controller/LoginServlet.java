package com.librarySystem.Demo.oldServlet.controller;

import entity.User;
import tool.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
//        request.setCharacterEncoding("utf-8");
        Integer identity = Integer.parseInt(request.getParameter("identity"));
        String sno = request.getParameter("sno");
        String password = request.getParameter("password");
        System.out.println(sno +"\t"+ password);
        String errMsg = "";
        User user = UserDAO.login(identity, sno, password);
        if (user != null) {
            errMsg = "OK";
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
        } else {
            errMsg = "用户名或密码不正确";
        }

        response.setContentType("text/html;charset=utf-8");
        response.getWriter().println(errMsg);
    }
}