package com.example.kamenpapiernoznice;

public class AppData {

    private static AppData instance;

    private AppData() {}

    private String userName;

    public String getUserName() {
        return userName;
    }

    public static AppData getInstance() {
        if(instance==null) instance = new AppData();
        return instance;
    }

}
