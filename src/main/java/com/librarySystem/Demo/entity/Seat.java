package com.librarySystem.Demo.entity;

import lombok.Data;

@Data
public class Seat
{

    private int seatid;
    private String campus;
    private int floor;
    private int deskno;
    private int desktype;
    private int occupynum;

    public Seat()
    {
    }

    public String getCampus()
    {
        return campus;
    }

    public void setCampus(String campus)
    {
        this.campus = campus;
    }

    public int getFloor()
    {
        return floor;
    }

    public void setFloor(int floor)
    {
        this.floor = floor;
    }

    public int getDeskno()
    {
        return deskno;
    }

    public void setDeskno(int deskno)
    {
        this.deskno = deskno;
    }

    public String getDeskInfo()
    {
        return this.campus + "-" + this.floor + "楼-" + this.deskno + "号桌";
    }
}
