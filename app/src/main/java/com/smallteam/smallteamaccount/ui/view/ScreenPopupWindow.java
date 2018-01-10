package com.smallteam.smallteamaccount.ui.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bo.customview.DateZoneChooserView;
import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.utils.DisplayUtil;
import com.smallteam.smallteamaccount.utils.EasyToast;
import com.smallteam.smallteamaccount.utils.WindowUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class ScreenPopupWindow extends PopupWindow implements PartsPopupWindow.PartsCheckNameChangedListener {
    private Activity mContext;
    private final TextView mPartsName;
    private final TextView mGridAll;
    private final TextView mGridDaily;
    private final TextView mGridHotel;
    private final TextView mGridEntertain;
    private final TextView mGridTravel;
    private final TextView mGridOhter;
    private final DateZoneChooserView mDzcv;

    public ScreenPopupWindow(Activity context) {
        super(context);
        mContext = context;
        int displayHeight = WindowUtils.getInstance(context).getDisplayHeight();

        View view = LayoutInflater.from(context).inflate(R.layout.popup_screen_layout, null);
        mPartsName = view.findViewById(R.id.screen_popup_parts_tv);
        mGridAll = view.findViewById(R.id.grid_all);
        mGridDaily = view.findViewById(R.id.grid_daily);
        mGridHotel = view.findViewById(R.id.grid_hotel);
        mGridEntertain = view.findViewById(R.id.grid_entertain);
        mGridTravel = view.findViewById(R.id.grid_travel);
        mGridOhter = view.findViewById(R.id.grid_other);
        mDzcv = view.findViewById(R.id.date_zone_chooser);

        initEvent();

        setContentView(view);
        setBackgroundDrawable(null);
        setHeight(displayHeight);
        setWidth(DisplayUtil.dip2px(context, 198));
        setFocusable(true);
        setAnimationStyle(R.style.popupWindow_animation_right);
        setOutsideTouchable(true);
        update();
    }



    private void initEvent() {
        mGridAll.setOnClickListener(v -> {
            mGridAll.setSelected(!mGridAll.isSelected());
        });
        mGridDaily.setOnClickListener(v -> {
            mGridDaily.setSelected(!mGridDaily.isSelected());
        });
        mGridHotel.setOnClickListener(v -> {
            mGridHotel.setSelected(!mGridHotel.isSelected());
        });
        mGridEntertain.setOnClickListener(v -> {
            mGridEntertain.setSelected(!mGridEntertain.isSelected());
        });
        mGridTravel.setOnClickListener(v -> {
            mGridTravel.setSelected(!mGridTravel.isSelected());
        });
        mGridOhter.setOnClickListener(v -> {
            mGridOhter.setSelected(!mGridOhter.isSelected());
        });

        mPartsName.setOnClickListener(v -> parts(v));
    }

    private void parts(View v) {
        List<String> list = new ArrayList<>();
        list.add("q");
        list.add("w");
        list.add("e");
        list.add("r");
        list.add("d");
        list.add("f");
        PartsPopupWindow partsPopupWindow = new PartsPopupWindow(mContext, list);
        partsPopupWindow.setPartsCheckNameChangedListener(this);
        if (!partsPopupWindow.isShowing()) {
            partsPopupWindow.showAsDropDown(mView);
        }
    }

    View mView;

    public void showRight(View v) {
        mView = v;
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        showAtLocation(v, Gravity.NO_GRAVITY, location[0] + v.getWidth(), location[1]);
    }

    @Override
    public void onCheckNameChanged(List<String> checkedName) {
        mPartsName.setText(checkedName.toString());
    }
}
