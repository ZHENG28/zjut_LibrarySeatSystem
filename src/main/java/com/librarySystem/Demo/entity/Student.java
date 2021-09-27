package com.librarySystem.Demo.entity;

public class Student extends User
{
    private String sno;
    private String sname;
    private String pswd;
    private int gender;
    private String campus;
    private String birthday;
    private int occupyseat;
    private int state;
    private int violatetime;
    private String IDadress;

    public Desk desk;

    public Student()
    {
    }

    // 初始化
    public Student(String sno, String sname, String pswd)
    {
        this.sno = sno;
        this.sname = sname;
        this.pswd = pswd;
        this.occupyseat = 0;
        this.state = 0;
        this.violatetime = 0;
    }

    public Student(String sno, String sname, String pswd, int gender, String campus, String birthday, int occupyseat, int state, int violatetime)
    {
        this.sno = sno;
        this.sname = sname;
        this.pswd = pswd;
        this.gender = gender;
        this.campus = campus;
        this.birthday = birthday;
        this.occupyseat = occupyseat;
        this.state = state;
        this.violatetime = violatetime;
    }

    public Student(String sno, String sname, String pswd, int gender, String campus, String birthday, int occupyseat, int state, int violatetime,String IDadress)
    {
        this.sno = sno;
        this.sname = sname;
        this.pswd = pswd;
        this.gender = gender;
        this.campus = campus;
        this.birthday = birthday;
        this.occupyseat = occupyseat;
        this.state = state;
        this.violatetime = violatetime;
        this.IDadress = IDadress;
    }

    public String getSno()
    {
        return sno;
    }

    public String getSname()
    {
        return sname;
    }

    public int getGender()
    {
        return gender;
    }

    public void setGender(int gender)
    {
        this.gender = gender;
    }

    public String getCampus()
    {
        return campus;
    }

    public void setCampus(String campus)
    {
        this.campus = campus;
    }

    public String getPswd()
    {
        return pswd;
    }

    public void setPswd(String pswd)
    {
        this.pswd = pswd;
    }

    public String getBirthday()
    {
        return birthday;
    }

    public void setBirthday(String birthday)
    {
        this.birthday = birthday;
    }

    public int getOccupyseat()
    {
        return occupyseat;
    }

    public void setOccupyseat(int occupyseat)
    {
        this.occupyseat = occupyseat;
    }

    public String getState()
    {
        return state == 0 ? "未选座" : (state == 1 ? "已选座" : "暂离");
    }

    public void setState(int state)
    {
        this.state = state;
    }

    public int getViolatetime()
    {
        return violatetime;
    }

    public void setViolatetime(int violatetime)
    {
        this.violatetime = violatetime;
    }

    public String getIDadress() {
        return IDadress;
    }

    public void setIDadress(String IDadress) {
        this.IDadress = IDadress;
    }

    @Override
    public String toString() {
        return "Student{" +
                "sno='" + sno + '\'' +
                ", sname='" + sname + '\'' +
                ", pswd='" + pswd + '\'' +
                ", gender=" + gender +
                ", campus='" + campus + '\'' +
                ", birthday='" + birthday + '\'' +
                ", occupyseat=" + occupyseat +
                ", state=" + state +
                ", violatetime=" + violatetime +
                ", IDadress='" + IDadress + '\'' +
                ", desk=" + desk +
                '}';
    }
}