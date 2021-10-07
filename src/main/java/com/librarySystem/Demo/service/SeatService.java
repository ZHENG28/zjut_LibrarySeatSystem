package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.HistoryDao;
import com.librarySystem.Demo.dao.SeatDao;
import com.librarySystem.Demo.dao.UserDao;
import com.librarySystem.Demo.entity.History;
import com.librarySystem.Demo.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
            double index = (double) seat.getOccupyNum() / (double) seat.getDeskType();
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
