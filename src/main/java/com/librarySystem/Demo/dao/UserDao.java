package com.librarySystem.Demo.dao;

import com.librarySystem.Demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao
{
    User findByIdentity(String identity, String account);

    User login(String identity, String account, String password);

//    int register(String sno, String sname, String password, String campus);
//
//    User searchUserById(String id);
}
