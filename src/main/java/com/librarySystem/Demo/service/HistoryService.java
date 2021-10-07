package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.HistoryDao;
import com.librarySystem.Demo.entity.History;
import com.librarySystem.Demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class HistoryService
{
    @Autowired
    HistoryDao historyDao;


    public History getHistoryByUser(User user)
    {
        return historyDao.getHistoryByUser(user.getId(), user.getSeatId(), user.getReserveTime());
    }
}