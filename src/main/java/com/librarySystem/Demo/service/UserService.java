package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.UserDAO;
import com.librarySystem.Demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public boolean login(String username,String password){
        if (userDAO.login(username,password)!=null) return true;
        return false;
    }

    public boolean register(){
        return false;
    }
}
