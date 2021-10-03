package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.AdminDao;
import com.librarySystem.Demo.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService
{
    @Autowired
    AdminDao adminDao;

    public Admin login(String account, String password)
    {
        return adminDao.login(account, password);
    }
}
