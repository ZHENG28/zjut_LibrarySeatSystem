package com.librarySystem.Demo.entity;

public class Manager extends User
{
    private String userId;
    private String userName;
    private String  userIdentity;
    private String userCampus;
    private String gender;
    private String birthday;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdtity(String userIdtity) {
        this.userIdentity = userIdtity;
    }

    public String getUserCampus() {
        return userCampus;
    }

    public void setUserCampus(String userCampus) {
        this.userCampus = userCampus;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Manager() {
    }

    public Manager(String userId, String userName, String userIdtity, String userCampus, String gender, String birthday) {
        this.userId = userId;
        this.userName = userName;
        this.userIdentity = userIdtity;
        this.userCampus = userCampus;
        this.gender = gender;
        this.birthday = birthday;
    }
}