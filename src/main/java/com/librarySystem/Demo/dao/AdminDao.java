package com.librarySystem.Demo.dao;

import com.librarySystem.Demo.entity.Admin;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDao
{
    Admin login(String account, String password);
}
