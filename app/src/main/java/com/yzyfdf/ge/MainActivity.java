package com.yzyfdf.ge;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;

import com.blankj.utilcode.util.ScreenUtils;

/**
 * @author MLRC-iOS-CI
 */
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
    }
}
