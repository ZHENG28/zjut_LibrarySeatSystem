package com.librarySystem.Demo.controller;

import com.librarySystem.Demo.service.LayoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/layout")
public class LayoutController
{
    @Autowired
    LayoutService layoutService;

    @GetMapping("/getList")
    @ResponseBody
    public Object seatInfo()
    {
        return layoutService.getLayout();
    }
}
