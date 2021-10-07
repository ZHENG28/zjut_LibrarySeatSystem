package com.librarySystem.Demo.dao;

import com.librarySystem.Demo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDao
{
    User findByIdentity(String identity, String account);

    User login(String identity, String account, String password);

    int reserve(@Param("userId") String userId, @Param("seatId") Integer seatId, @Param("time") Date time);

    int signOut(String userId);

    String findNameByUserId(String userid);

    List<User> getAll();

//    int register(String sno, String sname, String password, String campus);
//
//    User searchUserById(String id);
}
