package com.smallteam.smallteamaccount.ui.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;

import com.smallteam.smallteamaccount.R;

/**
 * Created by Administrator on 2018/1/3.
 */

public class ProgressDialogUtils extends ProgressDialog {
    private static ProgressDialogUtils mUtils;

    public static ProgressDialogUtils getInstance(Context context) {
        if (mUtils == null) {
            synchronized (ProgressDialogUtils.class) {
                if (mUtils == null) {
                    mUtils = new ProgressDialogUtils(context);
                }
            }
        }
        return mUtils;
    }

    private ProgressDialogUtils(Context context) {
        this(context, R.style.ProgressUtils);
    }

    private ProgressDialogUtils(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init(getContext());
    }

    private void init(Context context) {
        setContentView(R.layout.dialog_load);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().setAttributes(params);
    }

    public ProgressDialogUtils setCancelAble(boolean b) {
        setCancelable(b);
        return mUtils;
    }

    public ProgressDialogUtils setCanceledOutside(boolean b) {
        setCanceledOnTouchOutside(b);
        return mUtils;
    }

    public ProgressDialogUtils showProgress() {
        super.show();
        return mUtils;
    }
}
