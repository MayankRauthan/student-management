package com.example.infinity.all_student_details;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.firebase.database.PropertyName;

import java.util.ArrayList;

public class student_details_class {
    private String fees;
    private int doj;

    

    public void setDm(int dm) {
        this.dm = dm;
    }

    private int dm;
    private String name;
    private int status;
    String key;
    private String subject;
    private ArrayList<Integer> arr;


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }


    public String getSubject() {
        return subject;
    }

    @PropertyName("arr")
    public ArrayList<Integer> getArr() {
        return arr;
    }
    @PropertyName("arr")
    public void setArr(ArrayList<Integer> date) {
        if(arr==null)
            arr=date;
        else
            arr.addAll(date);
    }

    student_details_class(){

    }
    public student_details_class(String fees, int doj, int dm, int status, String name, String subject) {
        this.fees = fees;
        this.doj = doj;
        this.dm = dm;
        this.status = status;
        this.name = name;
        this.subject = subject;
        arr=new ArrayList<Integer>();
    }



    public int getStatus() {
        return status;
    }


    public String getFees() {
        return fees;
    }

    public int getDoj() {
        return doj;
    }

    public int getDm() {
        return dm;
    }

    public String getName() {
        return name;
    }

    public void setStatus(int a) {
        this.status = a;
    }


}
