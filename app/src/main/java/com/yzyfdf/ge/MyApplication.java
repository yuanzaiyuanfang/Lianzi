package com.yzyfdf.ge;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * Created by MLRC-iOS-CI on 2017/12/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
    }
}
