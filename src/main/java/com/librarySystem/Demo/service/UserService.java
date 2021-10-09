package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.HistoryDao;
import com.librarySystem.Demo.dao.TagDao;
import com.librarySystem.Demo.dao.UserDao;
import com.librarySystem.Demo.entity.History;
import com.librarySystem.Demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService
{
    @Autowired
    UserDao userDao;

    @Autowired
    HistoryDao historyDao;

    @Autowired
    TagDao tagDao;

    public boolean findByIdentity(String identity, String account)
    {
        return userDao.findByIdentity(identity, account) == null;
    }

    public User login(String identity, String username, String password)
    {
        return userDao.login(identity, username, password);
    }

    public Object getAllUser()
    {
        return userDao.getAll();
    }

    public Object getBtnState(String userId)
    {
        Map<String, Object> info = new HashMap<>();
        User user = userDao.getCurInfo(userId);

        int seatId = user.getSeatId();
        int state = user.getState();
        System.out.println(user);
        History history = historyDao.getHistoryByUser(user.getId(), user.getSeatId(), user.getReservetime());
        if (seatId == 0 && state == 0) { // 未选座
            info.put("signin", false);
            info.put("signout", false);
            info.put("stop", false);
            info.put("back", false);
        } else if (seatId != 0 && state != 0) { // 已选座
            switch (state) {
                case 1: // 已选座
                    if (history.getReachtime() != null) { // 已签到
                        info.put("signin", false);
                        info.put("signout", true);
                        info.put("stop", true);
                        info.put("back", false);
                    } else { // 未签到
                        info.put("signin", true);
                        info.put("signout", false);
                        info.put("stop", false);
                        info.put("back", false);
                    }
                    break;
                case 2: // 暂离
                    info.put("signin", false);
                    info.put("signout", true);
                    info.put("stop", false);
                    info.put("back", true);
                    break;
            }
        } else {
            throw new RuntimeException("seatId与state不相符合");
        }

        return info;
    }

    public Boolean updateState(User user, String str)
    {
        int state = "stop".equals(str) ? 2 : 1;
        return userDao.updateState(user.getId(), state) == 1;
    }

    public Boolean getState(String userid)
    {
        User user = userDao.getCurInfo(userid);
        return user.getState() != 0;
    }

    public String getLikeTag(String userid)
    {
        String liketag = userDao.getCurInfo(userid).getLiketag();
        String[] tagList = liketag.split(",");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < tagList.length; i++) {
            sb.append(tagDao.getTagName(Integer.parseInt(tagList[i])) + ", ");
        }
        return sb.substring(0, sb.length() - 2);
    }

//    public boolean register(String sno, String sname, String password, String campus)
//    {
//        return systemDao.register(sno, sname, password, campus) == 1;
//    }
}
