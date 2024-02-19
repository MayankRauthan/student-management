package com.example.infinity.class_activity;


import android.widget.ImageView;

public class class_members {
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public int getClasss() {
        return classs;
    }

    public void setClasss(int classs) {
        this.classs = classs;
    }

    public class_members(int img, int classs) {
        this.img = img;
        this.classs = classs;
    }

    int img;
    int classs;

}
