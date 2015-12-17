package com.shenyan.volleytestdemo;

import android.app.Application;

import com.china.library.VolleyInit.VolleyImageLoaderManager;
import com.china.library.VolleyInit.VolleyQueueManager;
import com.orhanobut.logger.Logger;

/**
 * Created by ShenYan on 15/11/26. Project VolleyTestDemo
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyQueueManager.getInstance().initRequestQueue(getApplicationContext());
        Logger.init("MyTest").methodCount(3);
    }
}
