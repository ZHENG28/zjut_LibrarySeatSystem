package com.librarySystem.Demo.entity;

public class Book {

    private int bookid;
    private String bookname;
    private String auther;
    private String publish;
    private String sort;
    private boolean state;
    private String position;

    public Book(int bookid, String bookname, String auther, String publish, String sort, String position) {
        this.bookid = bookid;
        this.bookname = bookname;
        this.auther = auther;
        this.publish = publish;
        this.sort = sort;
        this.state = true;
        this.position = position;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBookname() {
        return bookname;
    }

    public void setBookname(String bookname) {
        this.bookname = bookname;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookid=" + bookid +
                ", bookname='" + bookname + '\'' +
                ", auther='" + auther + '\'' +
                ", publish='" + publish + '\'' +
                ", sort='" + sort + '\'' +
                ", state=" + state +
                ", position='" + position + '\'' +
                '}';
    }


}
