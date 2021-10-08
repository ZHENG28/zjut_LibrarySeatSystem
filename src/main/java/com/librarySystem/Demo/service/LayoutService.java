package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.LayoutDao;
import com.librarySystem.Demo.entity.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LayoutService
{
    @Autowired
    LayoutDao layoutDao;

    public List<Layout> getLayout()
    {
        return layoutDao.getLayout();
    }
}
