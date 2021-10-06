package com.librarySystem.Demo.dao;

import com.librarySystem.Demo.entity.Seat;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeatDao
{
    List<Seat> getSeatInfo();

    Seat getSeatById(Integer seatId);

    int changeNum(Integer seatId);
}
