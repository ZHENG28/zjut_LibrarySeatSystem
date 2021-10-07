package com.librarySystem.Demo.dao;

import com.librarySystem.Demo.entity.Seat;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface SeatDao
{
    List<Seat> getSeatInfo();

    Seat getSeatById(Integer seatId);

    int upNum(Integer seatId);

    int downNum(Integer seatId);

    List<Seat> getSeatByCampus(String campus);

    int getNumByFloor(@Param("campus") String campus, @Param("floor") int floor);
}
