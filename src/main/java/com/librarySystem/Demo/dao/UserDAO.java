package com.librarySystem.Demo.dao;

import com.librarySystem.Demo.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDAO {

    User login(String sno, String password);

    int register(String sno,String sname, String password, String campus);
    //以下都没做
    User searchUserById(String id);


}
