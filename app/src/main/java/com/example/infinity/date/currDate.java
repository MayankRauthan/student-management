package com.example.infinity.date;

import java.util.Calendar;

public class currDate {
    public static int mYear;
    public  static int mMonth;
    public static int mDay;
    public static void setCurrDate(){
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR); // current year
        mMonth = c.get(Calendar.MONTH); // current month
        mDay = c.get(Calendar.DAY_OF_MONTH); // current day
    }
}
