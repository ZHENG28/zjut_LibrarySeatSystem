package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.UserDAO;
import com.librarySystem.Demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User login(String username,String password){
        return userDAO.login(username,password);
    }

    public boolean register(String sno,String sname, String password, String campus){
        if (userDAO.register(sno, sname, password, campus)==1) return true;
        return false;
    }
}
