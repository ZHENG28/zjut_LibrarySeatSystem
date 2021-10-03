package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.SeatDao;
import com.librarySystem.Demo.entity.Seat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatService {
    @Autowired
    SeatDao seatDAO;

    public List<Seat> getSeatInfo()
    {
        return seatDAO.getSeatInfo();
    }
}
