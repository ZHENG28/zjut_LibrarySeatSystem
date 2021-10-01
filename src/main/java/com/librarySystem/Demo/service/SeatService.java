package com.librarySystem.Demo.service;

import com.librarySystem.Demo.dao.SeatDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeatService {

    @Autowired
    SeatDAO seatDAO;

}
