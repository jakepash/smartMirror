package com.jakepash.jacob;

public class Weather {
    private int temp = 0;
    private int maxTemp = 0;
    private int minTemp = 0;
    private String icon = "clear-day";

    public void setTemp(int setTemp) {
        temp = setTemp;
    }
    public void setMaxTemp(int setMaxTemp) {
        maxTemp = setMaxTemp;
    }
    public void setMinTemp(int setMinTemp) {
        minTemp = setMinTemp;
    }
    public void setIcon(String setIcon) {
        icon = setIcon;
    }


    public int getTemp() {
        return temp;
    }
    public int getMaxTemp() {
        return  maxTemp;
    }
    public int getMinTemp() {
        return  minTemp;
    }
    public String getIcon() {
        return  icon;
    }




}
