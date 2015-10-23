package com.example.sid.twotaptrain;

import android.app.Application;
import android.content.Context;


public class MyApp extends Application {
    private  static MyApp sInstance;
    @Override
    public void onCreate(){
        super.onCreate();
        sInstance=this;
    }

    public static MyApp getsInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
