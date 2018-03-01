package com.cqjtu.domain;

import java.text.SimpleDateFormat;

/**
 * @author mevur
 * @date 18/3/1
 **/
public class Date extends java.util.Date {
    public Date() {
        super();
    }

    public Date(long date) {
        super(date);
    }

    public Date(int year, int month, int date) {
        super(year, month, date);
    }

    public Date(int year, int month, int date, int hrs, int min) {
        super(year, month, date, hrs, min);
    }

    public Date(int year, int month, int date, int hrs, int min, int sec) {
        super(year, month, date, hrs, min, sec);
    }

    public Date(String s) {
        super(s);
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        return format.format(this);
    }

    public static void main(String[] args) {
        Date date = new Date("2018-02-23");
        System.out.println(date);
    }
}
