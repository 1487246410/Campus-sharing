package com.example.lenovo.myapplication.Bean;

import android.app.Application;
import android.content.Context;

public class ShanYaoApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    /**
     * 获取全局的context
     */
    public static Context getContext() {
        return mContext;
    }

}
