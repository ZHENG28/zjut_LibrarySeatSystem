package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.HistoryDao;
import com.librarySystem.Demo.dao.SeatDao;
import com.librarySystem.Demo.dao.UserDao;
import com.librarySystem.Demo.entity.Seat;
import com.librarySystem.Demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class SeatService
{
    @Autowired
    SeatDao seatDao;

    @Autowired
    UserDao userDao;

    @Autowired
    HistoryDao historyDao;

    private double idleIndex = 0.5;

    private double fullIndex = 0.75;

    public List<Seat> getSeatInfo()
    {
        return seatDao.getSeatInfo();
    }

    public Seat getSeatById(Integer seatId)
    {
        return seatDao.getSeatById(seatId);
    }

    public Object getIdleList(String campus, int floorNum)
    {
        List<Seat> seatList = seatDao.getSeatByCampus(campus);
        int[] idleList = new int[floorNum];
        for (Seat seat : seatList) {
            double index = (double) seat.getOccupynum() / (double) seat.getDesktype();
            if (index <= idleIndex) {
                int i = seat.getFloor() - 1;
                idleList[i] += 1;
            }
        }
        return idleList;
    }

    public Object getState(String campus, int floorNum)
    {
        String[] stateList = new String[floorNum];
        int[] idleList = (int[]) this.getIdleList(campus, floorNum);
        for (int i = 0; i < floorNum; i++) {
            double index = (double) idleList[i] / (double) seatDao.getNumByFloor(campus, i + 1);
            stateList[i] = index <= 0.5 ? "空闲" : (index <= 0.85 ? "较满" : "已满");
        }
        return stateList;
    }

    @Transactional
    public Boolean reserve(String userId, Integer seatId, Date time)
    {
        int i = 0;
        // 修改用户信息
        i += userDao.reserve(userId, seatId, time);
        // 添加选座历史记录
        i += historyDao.insert(userId, seatId, time);
        // 修改座位人数
        i += seatDao.upNum(seatId);

        return i == 3;
    }

    @Transactional
    public Boolean signOut(User user, Date date)
    {
        int seatId = user.getSeatId();
        String userId = user.getId();

        int i = 0;
        // 修改用户信息
        i += userDao.signOut(userId);
        // 修改选座历史记录
        i += historyDao.updateSignout(userId, seatId, user.getReservetime(), date);
        // 修改座位人数
        i += seatDao.downNum(seatId);

        return i == 3;
    }
}
