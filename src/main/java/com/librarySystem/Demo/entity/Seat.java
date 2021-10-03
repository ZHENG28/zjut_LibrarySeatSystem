package com.librarySystem.Demo.entity;

import lombok.Data;

@Data
public class Seat {

    private int seatId;
    private String campus;
    private int floor;
    private int deskno;
    private int deskType;
    private int occupyNum;

    public Seat()
    {
    }

    public Seat(int seatId, String campus, int floor, int deskno, int deskType, int occupyNum)
    {
        this.seatId = seatId;
        this.campus = campus;
        this.floor = floor;
        this.deskno = deskno;
        this.deskType = deskType;
        this.occupyNum = occupyNum;
    }

    public int getSeatId()
    {
        return seatId;
    }

    public void setSeatId(int seatId)
    {
        this.seatId = seatId;
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

    public int getDeskType()
    {
        return deskType;
    }

    public void setDeskType(int deskType)
    {
        this.deskType = deskType;
    }

    public int getOccupyNum()
    {
        return occupyNum;
    }

    public void setOccupyNum(int occupyNum)
    {
        this.occupyNum = occupyNum;
    }

    public String getDeskInfo()
    {
        String campus = "ZH".equals(this.campus) ? "朝晖" : ("PF".equals(this.campus) ? "屏峰" : "莫干山");
        return campus + "-" + this.floor + "楼-" + this.deskno + "号桌";
    }
}
