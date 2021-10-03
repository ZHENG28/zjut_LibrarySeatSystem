package com.librarySystem.Demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class History
{
    private int id;
    private String userId;
    private int seatId;
    private Date reservation;
    private Date reachTime;
    private Date leaveTime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Date getReservation()
    {
        return reservation;
    }

    public void setReservation(Date reservation)
    {
        this.reservation = reservation;
    }
}
