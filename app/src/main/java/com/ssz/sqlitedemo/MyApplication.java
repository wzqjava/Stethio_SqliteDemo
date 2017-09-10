package com.ssz.sqlitedemo;

import android.app.Application;

import com.facebook.stetho.Stetho;

/**
 * Created by Administrator on 2017/9/9/009.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
