package com.librarySystem.Demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChooseController
{
    @GetMapping("/toZHfloor")
    public String ZHfloor()
    {
        return "views/zhFloorView";
    }

    @GetMapping("/toPFfloor")
    public String PFfloor()
    {
        return "views/pfFloorView";
    }

    @GetMapping("/toMGSfloor")
    public String MGSfloor()
    {
        return "views/mgsFloorView";
    }
}
