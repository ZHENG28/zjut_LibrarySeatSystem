package com.librarySystem.Demo.dao;

import com.librarySystem.Demo.entity.History;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface HistoryDao
{
    int insert(@Param("userId") String userId, @Param("seatId") Integer seatId, @Param("time") Date time);

    History getHistoryByUser(@Param("userId") String id, @Param("seatId") int seatId, @Param("reserveTime") Date reserveTime);

    List<History> getAllUserHistory(@Param("userId") String id);

    int updateSignin(@Param("id") String id, @Param("seatId") int seatId, @Param("reservetime") Date reservetime, @Param("date") Date date);

    int updateSignout(@Param("id") String id, @Param("seatId") int seatId, @Param("reservetime") Date reservetime, @Param("date") Date date);

    List<History> getAll();

    double getHistoryByTag(@Param("userid") String userid, @Param("tag") String tag);

    List<String> getUserByTag(String maxTagId);
}
