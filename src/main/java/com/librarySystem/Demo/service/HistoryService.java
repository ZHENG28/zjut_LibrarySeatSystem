package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.HistoryDao;
import com.librarySystem.Demo.dao.SeatDao;
import com.librarySystem.Demo.dao.UserDao;
import com.librarySystem.Demo.entity.History;
import com.librarySystem.Demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class HistoryService
{
    @Autowired
    HistoryDao historyDao;

    @Autowired
    SeatDao seatDao;

    @Autowired
    UserDao userDao;

    public List<Map<String, Object>> getAllByUserId(String userid)
    {
        List<History> list = historyDao.getAllUserHistory(userid);
        List<Map<String, Object>> historyList = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (History h : list) {
            Map<String, Object> history = new HashMap<>();
            h.setSeat(seatDao.getSeatById(h.getSeatid()));
            history.put("id", h.getId());
            history.put("seatinfo", h.getSeat().getDeskInfo());
            history.put("reservation", fmt.format(h.getReservation()));
            history.put("reachtime", h.getReachtime() != null ? fmt.format(h.getReachtime()) : "");
            history.put("leavetime", h.getLeavetime() != null ? fmt.format(h.getLeavetime()) : "");
            historyList.add(history);
        }
        return historyList;
    }

    public Boolean signIn(String id, int seatId, Date reservetime, Date date)
    {
        return historyDao.updateSignin(id, seatId, reservetime, date) == 1;
    }

    public List<Map<String, Object>> getAllUserHistory()
    {
        List<History> list = historyDao.getAll();
        List<Map<String, Object>> historyList = new ArrayList<>();
        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (History h : list) {
            Map<String, Object> history = new HashMap<>();
            h.setSeat(seatDao.getSeatById(h.getSeatid()));
            history.put("id", h.getId());
            history.put("username", userDao.findNameByUserId(h.getUserid()));
            history.put("seatinfo", h.getSeat().getDeskInfo());
            history.put("reservation", fmt.format(h.getReservation()));
            history.put("reachtime", h.getReachtime() != null ? fmt.format(h.getReachtime()) : "");
            history.put("leavetime", h.getLeavetime() != null ? fmt.format(h.getLeavetime()) : "");
            historyList.add(history);
        }
        return historyList;
    }
}
