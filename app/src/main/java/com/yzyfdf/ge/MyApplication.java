package com.yzyfdf.ge;

import android.app.Application;

import com.blankj.utilcode.util.Utils;
import com.lzy.okgo.OkGo;

/**
 * Created by MLRC-iOS-CI on 2017/12/8.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Utils.init(this);
        OkGo.getInstance().init(this);
    }
}
