package com.librarySystem.Demo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class User
{
    private String id;
    private String name;
    private String password;
    // 0-老师；1-学生
    private int identity;
    // 0-男；1-女
    private int gender;
    private String campus;
    private String faculty;
    private String liketag;
    private int seatId;
    private Date reservetime;
    // 0-未占座；1-已选座；2-暂离
    private int state;
    private int violate;

    public Seat seat;

//    public Seat(String sno, String sname, String pswd)
//    {
//        this.sno = sno;
//        this.sname = sname;
//        this.pswd = pswd;
//        this.occupyseat = 0;
//        this.state = 0;
//        this.violatetime = 0;
//    }
}
