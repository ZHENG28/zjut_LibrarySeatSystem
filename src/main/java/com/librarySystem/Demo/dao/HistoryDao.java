package com.librarySystem.Demo.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface HistoryDao
{
    int insert(@Param("userId") String userId, @Param("seatId") Integer seatId, @Param("time") Date time);
}