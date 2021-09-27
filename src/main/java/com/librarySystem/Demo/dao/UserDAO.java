package com.librarySystem.Demo.dao;

import com.librarySystem.Demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    User login(String username, String password);

    //以下都没做
    User searchUserById(String id);

    int register(String username, String password, String campus);
}
