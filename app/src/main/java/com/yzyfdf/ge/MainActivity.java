package com.yzyfdf.ge;

import android.Manifest;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ScreenUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Response;

import java.io.File;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * @author MLRC-iOS-CI
 */
@RuntimePermissions
public class MainActivity extends AppCompatActivity implements View.OnLongClickListener, MyDialog.DialogClickCallBack {

    private int mScreenWidth;
    private int mScreenHeight;

    public static final int side = 50;//范围30-80
    public static final int sideMin = 30;
    public static int sideNow = 50;
    private FrameLayout mRootView;
    private Ge mGe;
    private MyDialog mSeekBarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().setNavigationBarColor(Color.TRANSPARENT);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        mRootView = (FrameLayout) findViewById(R.id.rootView);
        mRootView.setOnLongClickListener(this);

        mScreenWidth = ScreenUtils.getScreenWidth();
        mScreenHeight = ScreenUtils.getScreenHeight();
        System.out.println("screenWidth = " + mScreenWidth);
        System.out.println("screenHeight = " + mScreenHeight);

        mGe = new Ge(this);
        mGe.setScreenWidth(mScreenWidth);
        mGe.setScreenHeight(mScreenHeight);
        mGe.setSize(sideNow);

        mGe.invalidate();
        mRootView.addView(mGe, 0);

    }

    private void invalidate() {
        mGe.invalidate();
    }


    @Override
    public boolean onLongClick(View view) {
        if (mSeekBarDialog == null) {
            mSeekBarDialog = new MyDialog(this, R.style.MyAlertDialog);
        }
        mSeekBarDialog.showSeekBar(this, sideNow, sideMin, sideMin + side);
        return false;
    }


    @Override
    public void positiveClick(SettingBean bean) {
        mGe.setScreenWidth(ScreenUtils.getScreenWidth());
        mGe.setScreenHeight(ScreenUtils.getScreenHeight());
        sideNow = bean.getSize();
        mGe.reSet(bean);
        invalidate();

        MainActivityPermissionsDispatcher.downloadWithPermissionCheck(this);
//        download();
    }

    @NeedsPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
    void download() {
        System.out.println("开始下载");
        OkGo.<File>get("https://github.com/yuanzaiyuanfang/Lianzi/blob/master/README.md")
                .tag("")
                .execute(new FileCallback() {
                    @Override
                    public void onSuccess(Response<File> response) {
                        System.out.println("下载完成");
                    }
                });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // NOTE: delegate the permission handling to generated method
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
