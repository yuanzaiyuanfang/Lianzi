package com.yzyfdf.ge;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import com.blankj.utilcode.util.ScreenUtils;


/**
 * Created by MLRC-iOS-CI on 2017/12/8.
 */

public class MyDialog extends Dialog implements SeekBar.OnSeekBarChangeListener {

    private TextView mCancel;
    private TextView mCommit;
    private SeekBar mSeekbar;
    private TextView mProgress;
    private int mMin;
    private EditText mInput;

    protected MyDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.seekbardialog, null);
        setContentView(rootView);

        mCancel = rootView.findViewById(R.id.cancel);
        mCommit = rootView.findViewById(R.id.commit);
        mSeekbar = rootView.findViewById(R.id.seekbar);
        mProgress = rootView.findViewById(R.id.progress);
        mInput = rootView.findViewById(R.id.input);

        mSeekbar.setOnSeekBarChangeListener(this);

        ViewGroup.LayoutParams params = rootView.getLayoutParams();
        params.width = (int) (ScreenUtils.getScreenWidth() * 0.8);
        rootView.setLayoutParams(params);

        setCanceledOnTouchOutside(false);
        setCancelable(true);
    }

    public void showSeekBar(final DialogClickCallBack clickCallBack, int progress, final int min, int max) {
        mMin = min;
        mSeekbar.setMax(max - min);
        if (progress >= min && progress <= max) {
            mSeekbar.setProgress(progress - min);
            mProgress.setText(progress + "");
        }

        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        mCommit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickCallBack.positiveClick(mSeekbar.getProgress() + mMin, mInput.getText().toString().trim());

            }
        });

        show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        mProgress.setText(mSeekbar.getProgress() + mMin + "");
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


    public interface DialogClickCallBack {

        void positiveClick(int what, String text);

//        void negativeClick();
    }

}
