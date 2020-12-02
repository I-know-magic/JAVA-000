package com.example.readwriteseparationv1.config;

public enum DataSourceType {
    PRIMARY("primary"),SECOND("second"),THREE("three");
    private String str="";

    DataSourceType(String s) {
        this.str=s;
    }
    public String getType(){
        return str;
    }
}
