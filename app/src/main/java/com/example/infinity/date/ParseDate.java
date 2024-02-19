package com.example.infinity.date;

import android.util.Log;

public class ParseDate {
    private int date;
    public ParseDate(int date)
    {
        this.date=date;
    }
    public String getStringDate(){
        return date/1000000+"/"+(date/10000)%100+"/"+date%10000;
    }
    public int getMonth()
    {
        return (date/10000)%100;
    }
    public int getDate()
    {
        return date/1000000;
    }
    public int getYear(){
        return date%10000;
    }
    public int getdueDays(){
        int year=(currDate.mYear-getYear())*12;
        int month=currDate.mMonth-getMonth();
        int day=getDate()-currDate.mDay;
        return Math.abs((year+month)*30-day);

        }
    }


