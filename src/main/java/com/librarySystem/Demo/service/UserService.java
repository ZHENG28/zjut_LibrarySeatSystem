package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.UserDao;
import com.librarySystem.Demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    UserDao userDao;

    public boolean findByIdentity(String identity, String account)
    {
        return userDao.findByIdentity(identity, account) == null;
    }

    public User login(String identity, String username, String password)
    {
        return userDao.login(identity, username, password);
    }

    public Object getAllUser()
    {
        return userDao.getAll();
    }

//    public boolean register(String sno, String sname, String password, String campus)
//    {
//        return systemDao.register(sno, sname, password, campus) == 1;
//    }
}
