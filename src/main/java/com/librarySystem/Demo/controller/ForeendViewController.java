package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.dao.UserDao;
import com.librarySystem.Demo.entity.User;
import com.librarySystem.Demo.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/foreend")
public class ForeendViewController
{
    @Autowired
    SeatService seatService;

    @Autowired
    UserDao userDao;

    String v = "foreend/";

    @GetMapping("/userInfo")
    public String userInfo()
    {
        return v + "userInfo";
    }

    // 主页
    @GetMapping("/homePage")
    public String homePage()
    {
        return v + "homePage";
    }

    @GetMapping("/seatView")
    public String seatView()
    {
        return v + "seat/seatView";
    }

    // 座位信息
    @GetMapping("/qrcode")
    public ModelAndView qrcode(HttpServletRequest request)
    {
        ModelAndView mv = new ModelAndView();
        User user = (User) request.getSession().getAttribute("user");
        user = userDao.getCurInfo(user.getId());
        user.setSeat(seatService.getSeatById(user.getSeatId()));

        String curState = "";
        switch (user.getState()) {
            case 0:
                curState = "尚未选座";
                break;
            case 1:
                curState = "已选座";
                break;
            case 2:
                curState = "暂离座位";
                break;
        }
        mv.addObject("state", curState);
        mv.addObject("violate", user.getViolate() + "次");
        mv.addObject("seatInfo", user.getSeat() != null ? user.getSeat().getDeskInfo() : "");
        mv.setViewName(v + "qrcode");
        return mv;
    }

    @GetMapping("/seatInfo")
    public String seatInfo()
    {
        return v + "seatInfo";
    }

    @GetMapping("/history")
    public String history()
    {
        return v + "history";
    }

    // 其他工具
    @GetMapping("/searchBook")
    public String searchBook()
    {
        return v + "searchBook";
    }

    @GetMapping("/leaveMsg")
    public String leaveMsg()
    {
        return v + "leaveMsg";
    }

    // other
    @GetMapping("/form")
    public String form()
    {
        return v + "form";
    }

    @GetMapping("/guide")
    public String guide()
    {
        return v + "guide";
    }

    @GetMapping("/operaterule")
    public String operaterule()
    {
        return v + "operaterule";
    }
}
