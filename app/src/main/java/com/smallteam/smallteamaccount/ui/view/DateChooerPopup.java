package com.smallteam.smallteamaccount.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.utils.DisplayUtil;

/**
 * Created by Administrator on 2018/1/12.
 */

public class DateChooerPopup extends PopupWindow {
    public DateChooerPopup(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.parts_popup, null);


        setContentView(view);
        setBackgroundDrawable(null);
        setHeight(DisplayUtil.dip2px(context, 320));
        setWidth(DisplayUtil.dip2px(context, 198));
        setFocusable(true);
        setAnimationStyle(R.style.popupWindow_animation_top);
        setOutsideTouchable(true);
    }
}
