package com.librarySystem.Demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class History
{
    private int id;
    private String userid;
    private int seatid;
    private Date reservation;
    private Date reachtime;
    private Date leavetime;

    private Seat seat;

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
