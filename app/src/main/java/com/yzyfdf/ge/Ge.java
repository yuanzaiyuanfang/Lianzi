package com.yzyfdf.ge;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.view.View;

import com.blankj.utilcode.util.SizeUtils;

/**
 * Created by MLRC-iOS-CI on 2017/12/8.
 */

public class Ge extends View {

    private final Paint mPaint;
    private final Paint mTextPaint;
    private int mScreenWidth;
    private int mScreenHeight;
    private int mSide;
    public static final int crude = 4;
    public static final int fine = 2;
    private String mText = "";

    public Ge(Context context) {
        super(context);
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint();
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setColor(Color.LTGRAY);
        mTextPaint.setStyle(Paint.Style.FILL);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawGe(canvas);
    }

    private void drawGe(Canvas canvas) {
        int leng = SizeUtils.dp2px(mSide);
        int widthNum = mScreenWidth / leng;//列数
        int heightNum = mScreenHeight / leng;//行数
        int widthForm = widthNum * leng;//宽
        int heightForm = heightNum * leng;//高
        int wifthDeviation = (mScreenWidth - widthForm) / 2;//横向偏移
        int heightDeviation = (mScreenHeight - heightForm) / 2;//纵向偏移


        System.out.println("leng = " + leng);
        long time1 = System.currentTimeMillis();


        //纵
        mPaint.setColor(Color.BLACK);
        for (int i = 0; i <= widthNum; i++) {
            int start = leng * i;
            //粗
            mPaint.setStrokeWidth(crude);
            canvas.drawLine(start + wifthDeviation, heightDeviation, start + wifthDeviation, heightForm + heightDeviation, mPaint);
            //细
            if (i == widthNum) {
                break;
            }
            mPaint.setStrokeWidth(fine);
            canvas.drawLine(start + wifthDeviation + leng / 2, heightDeviation, start + wifthDeviation + leng / 2, heightForm + heightDeviation, mPaint);
        }
        //横
        mPaint.setColor(Color.BLACK);
        for (int i = 0; i <= heightNum; i++) {
            int start = leng * i;
            //粗
            mPaint.setStrokeWidth(crude);
            canvas.drawLine(wifthDeviation, start + heightDeviation, widthForm + wifthDeviation, start + heightDeviation, mPaint);
            if (i == heightNum) {
                break;
            }
            //细
            mPaint.setStrokeWidth(fine);
            canvas.drawLine(wifthDeviation, start + heightDeviation + leng / 2, widthForm + wifthDeviation, start + heightDeviation + leng / 2, mPaint);
        }

        //斜
        mPaint.setColor(Color.RED);
        PathEffect effects = new DashPathEffect(new float[]{5, 5, 5, 5}, 1);
        mPaint.setPathEffect(effects);
        for (int i = 0; i <= widthNum; i++) {
            int start = leng * i;
            //右斜
            Path path = new Path();
            path.moveTo(start + wifthDeviation, heightDeviation);
            path.lineTo(widthForm + wifthDeviation, widthForm - start + heightDeviation);
            canvas.drawPath(path, mPaint);
            //左斜
            path.moveTo(start + wifthDeviation, heightDeviation);
            path.lineTo(wifthDeviation, start + heightDeviation);
            canvas.drawPath(path, mPaint);
        }

        for (int i = 0; i <= heightNum; i++) {
            int start = leng * i;
            //左斜
            Path path = new Path();
            int startY = start + heightDeviation;
            path.moveTo(widthForm + wifthDeviation, startY);
            if (heightForm - start >= widthForm) {
                path.lineTo(wifthDeviation, start + heightDeviation + widthForm);
            } else {
                path.lineTo(widthForm - (heightForm - start) + wifthDeviation, heightForm + heightDeviation);
            }
            canvas.drawPath(path, mPaint);

            path.moveTo(wifthDeviation, startY);
            if (heightForm - start >= widthForm) {
                path.lineTo(widthForm + wifthDeviation, start + widthForm + heightDeviation);
            } else {
                path.lineTo(heightForm - start + wifthDeviation, heightForm + heightDeviation);
            }
            canvas.drawPath(path, mPaint);
        }

        //字
        mTextPaint.setTextSize((float) (leng * 0.7));
        Paint.FontMetricsInt metricsInt = mTextPaint.getFontMetricsInt();
        H:
        for (int i = 0; i < heightNum; i++) {
            for (int j = 0; j < widthNum; j++) {
                String s = "";
                try {
                    s = mText.substring(widthNum * i + j, widthNum * i + j + 1);
                } catch (Exception e) {
                    break H;
                }
                int x = wifthDeviation + leng * j + leng / 2;
                int y = heightDeviation + (leng * i + leng * (i + 1) - metricsInt.top - metricsInt.bottom) / 2;
                canvas.drawText(s, x, y, mTextPaint);
            }
        }


        long time2 = System.currentTimeMillis();
        System.out.println("drawTime:" + (time2 - time1));
    }

    public void setScreenWidth(int screenWidth) {
        mScreenWidth = screenWidth;
    }

    public void setScreenHeight(int screenHeight) {
        mScreenHeight = screenHeight;
    }

    public void setSize(int side) {
        mSide = side;
    }


    public void setText(String text) {
        mText = text;
    }
}
