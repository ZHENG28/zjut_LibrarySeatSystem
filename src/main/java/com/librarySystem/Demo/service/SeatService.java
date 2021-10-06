package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.HistoryDao;
import com.librarySystem.Demo.dao.SeatDao;
import com.librarySystem.Demo.dao.UserDao;
import com.librarySystem.Demo.entity.History;
import com.librarySystem.Demo.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class SeatService
{
    @Autowired
    SeatDao seatDao;

    @Autowired
    UserDao userDao;

    @Autowired
    HistoryDao historyDao;

    public List<Seat> getSeatInfo()
    {
        return seatDao.getSeatInfo();
    }

    public Seat getSeatById(Integer seatId)
    {
        return seatDao.getSeatById(seatId);
    }

    @Transactional
    public Boolean reserve(String userId, Integer seatId, Date time)
    {
        // 修改用户信息
        userDao.reserve(userId, seatId, time);
        // 添加选座历史记录
        historyDao.insert(userId, seatId, time);
        // 修改座位人数
        seatDao.changeNum(seatId);

        // 全部完成成功后，提交事务，返回true
        // 否则返回false
        return false;
    }
}
