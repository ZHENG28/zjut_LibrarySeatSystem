package com.librarySystem.Demo.entity;

import java.util.Date;

public class History
{
    private int id;
    private String sno;
    private int occupyseat;
    private Date reservation;
    private Date reachtime;
    private Date leavetime;

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getSno()
    {
        return sno;
    }

    public void setSno(String sno)
    {
        this.sno = sno;
    }

    public int getOccupyseat()
    {
        return occupyseat;
    }

    public void setOccupyseat(int occupyseat)
    {
        this.occupyseat = occupyseat;
    }

    public Date getReservation()
    {
        return reservation;
    }

    public void setReservation(Date reservation)
    {
        this.reservation = reservation;
    }

    public Date getReachtime()
    {
        return reachtime;
    }

    public void setReachtime(Date reachtime)
    {
        this.reachtime = reachtime;
    }

    public Date getLeavetime()
    {
        return leavetime;
    }

    public void setLeavetime(Date leavetime)
    {
        this.leavetime = leavetime;
    }
}
