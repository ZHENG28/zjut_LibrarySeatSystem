package com.librarySystem.Demo.oldServlet.controller.user;

import dao.UserDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ModifyPWDServlet extends HttpServlet
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String sno = request.getParameter("sno");
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
//        System.out.println(sno + "\t" + oldPwd + "\t" + newPwd);

        if (UserDAO.modifyPwd(sno, oldPwd, newPwd) == 1) {
            System.out.println("success");
        } else {
            // 修改失败
            System.out.println("fail");
        }
        request.getRequestDispatcher("/main/library.jsp").forward(request, response);
    }
}
