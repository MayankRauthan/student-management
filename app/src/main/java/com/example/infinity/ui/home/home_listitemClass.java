package com.example.infinity.ui.home;

public class home_listitemClass {
    public home_listitemClass(){}
   public home_listitemClass(int NOS,int NOPS,int TF,String clss){
       this.NOPS=NOPS;
       this.NOS=NOS;
       this.TF=TF;
       this.clss=clss;

   }
    public int getNOS() {
        return NOS;
    }

    public int getNOPS() {
        return NOPS;
    }

    public int getTF() {
        return TF;
    }

    private int NOS;
    private int NOPS;
    private int TF;

    public String getClss() {
        return clss;
    }

    public void setClss(String clss) {
        this.clss = clss;
    }

    private String clss;


    public void setNOS(int NOS) {
        this.NOS = NOS;
    }

    public void setNOPS(int NOPS) {
        this.NOPS = NOPS;
    }

    public void setTF(int TF) {
        this.TF = TF;
    }
}
