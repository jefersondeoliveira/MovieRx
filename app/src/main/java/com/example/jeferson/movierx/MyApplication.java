package com.example.jeferson.movierx;

import android.app.Application;

/**
 * Created by jeferson on 12/09/16.
 */
public class MyApplication extends Application{

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

}
