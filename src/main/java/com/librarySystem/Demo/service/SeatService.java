package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.HistoryDao;
import com.librarySystem.Demo.dao.SeatDao;
import com.librarySystem.Demo.dao.TagDao;
import com.librarySystem.Demo.dao.UserDao;
import com.librarySystem.Demo.entity.Seat;
import com.librarySystem.Demo.entity.Tag;
import com.librarySystem.Demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.DoubleStream;

@Service
public class SeatService
{
    @Autowired
    SeatDao seatDao;

    @Autowired
    UserDao userDao;

    @Autowired
    HistoryDao historyDao;

    @Autowired
    TagDao tagDao;

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

    public Map<String, Object> update(String id)
    {
        Map<String, Object> info = new HashMap<>();
        User user = userDao.getCurInfo(id);
        user.setSeat(getSeatById(user.getSeatId()));

        String curState = "";
        switch (user.getState()) {
            case 0:
                curState = "尚未选座";
                break;
            case 1:
                curState = "已选座";
                break;
            case 2:
                curState = "暂离座位";
                break;
        }
        info.put("state", curState);
        info.put("violate", user.getViolate() + "次");
        info.put("seatInfo", user.getSeat() != null ? user.getSeat().getDeskInfo() : "");
        return info;
    }

    public double[] getUserWeight(String userid, List<Tag> tagList)
    {
        double[] selfWeight = new double[tagList.size()];
        for (Tag tag : tagList) {
            String str = "%" + tag.getId() + ",%";
            selfWeight[tag.getId() - 1] = historyDao.getHistoryByTag(userid, str);
        }
        return selfWeight;
    }

    public List<Seat> getIdleSeat(List<Tag> tagList, double[] tagWeightList)
    {
        List<Map<String, Object>> seatWeight = new ArrayList<>();
        List<Seat> seatList = new ArrayList<>();

        for (int i = 0; i < tagList.size(); i++) {
            String str = "%" + (i + 1) + ",%";
            List<Seat> idleSeat = seatDao.getSeatByTag(str);
            for (Seat seat : idleSeat) {
                Map<String, Object> map = new HashMap<>();
                map.put("seat", seat);

                String[] tagStr = seat.getTag().split(",");
                double weight = 0;
                for (int j = 0; j < tagStr.length; j++) {
                    int tag = Integer.parseInt(tagStr[j]);
                    weight += tagWeightList[tag - 1];
                }
                map.put("weight", weight);

                seatWeight.add(map);
            }
        }
        // 去重
        seatWeight = removeDuplicate(seatWeight);
        // 降序
        seatWeight.sort(new Comparator<Map<String, Object>>()
        {
            public int compare(Map<String, Object> o1, Map<String, Object> o2)
            {
                Double weight1 = (Double) o1.get("weight");
                Double weight2 = (Double) o2.get("weight");
                return weight2.compareTo(weight1);
            }
        });

        // 取前五个
        seatWeight = seatWeight.subList(0, 5);
        for (Map<String, Object> map : seatWeight) {
            seatList.add((Seat) map.get("seat"));
        }

        return seatList;
    }

    public static List removeDuplicate(List list)
    {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = list.size() - 1; j > i; j--) {
                if (((Seat) ((Map) list.get(j)).get("seat")).getSeatid() == (((Seat) ((Map) list.get(i)).get("seat")).getSeatid())) {
                    list.remove(j);
                }
            }
        }
        return list;
    }

    public double[] getFacultyWeight(User user, List<Tag> tagList)
    {
        double[] selfWeight = this.getUserWeight(user.getId(), tagList);
        double[] facultyWeight = new double[tagList.size()];
        List<User> userList = userDao.getUserByFaculty(user.getFaculty());
        userList.remove(user);
        for (User u : userList) {
            double[] userWeight = this.getUserWeight(u.getId(), tagList);
            for (int i = 0; i < tagList.size(); i++) {
                facultyWeight[i] += (selfWeight[i] - userWeight[i]) / selfWeight[i] * userWeight[i];
            }
        }
        return facultyWeight;
    }

    public double[] getSameTagWeight(User user, List<Tag> tagList)
    {
        double[] selfWeight = this.getUserWeight(user.getId(), tagList);
        int maxTagId = 0;
        for (int i = 0; i < selfWeight.length; i++) {
            if (selfWeight[i] > selfWeight[maxTagId]) {
                maxTagId = i;
            }
        }
        double[] sameTagWeight = new double[tagList.size()];

        String str = "%" + (maxTagId + 1) + ",%";
        List<String> userList = historyDao.getUserByTag(str);
        userList.remove(user.getId());
        for (String id : userList) {
            double[] userWeight = this.getUserWeight(id, tagList);
            for (int i = 0; i < tagList.size(); i++) {
                sameTagWeight[i] += (selfWeight[i] - userWeight[i]) / selfWeight[i] * userWeight[i];
            }
        }

        return sameTagWeight;
    }

    public List<Seat> personalRecommend(String id)
    {
        List<Tag> tagList = tagDao.getAllTag();
        User user = userDao.getCurInfo(id);
        double[] tagWeight = new double[tagList.size()];

        // weight = 0.7
        double[] selfWeight = getUserWeight(id, tagList);
        // weight = 0.15
        double[] facultyWeight = getFacultyWeight(user, tagList);
        // weight = 0.15
        double[] sameTagWeight = getSameTagWeight(user, tagList);

        for (int i = 0; i < tagList.size(); i++) {
            tagWeight[i] += selfWeight[i] * 0.7 + facultyWeight[i] * 0.15 + sameTagWeight[i] * 0.15;
            if (user.getLiketag() != null && Pattern.compile((i + 1) + ",").matcher(user.getLiketag()).find()) {
                tagWeight[i] *= 1.1;
            }
        }

        return getIdleSeat(tagList, tagWeight);
    }
}
