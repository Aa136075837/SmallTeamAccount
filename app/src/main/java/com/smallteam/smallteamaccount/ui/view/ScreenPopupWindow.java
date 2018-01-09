package com.smallteam.smallteamaccount.ui.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.utils.DisplayUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class ScreenPopupWindow extends PopupWindow implements PartsPopupWindow.PartsCheckNameChangedListener {
    private Context mContext;
    private final TextView mPartsName;

    public ScreenPopupWindow(Context context) {
        super(context);
        mContext = context;
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = manager.getDefaultDisplay().getHeight();

        View view = LayoutInflater.from(context).inflate(R.layout.popup_screen_layout, null);
        mPartsName = view.findViewById(R.id.screen_popup_parts_tv);
        mPartsName.setOnClickListener(v -> parts(v));

        setContentView(view);
        setHeight(height);
        setWidth(DisplayUtil.dip2px(context, 198));
        setFocusable(true);
        setAnimationStyle(R.style.popupWindow_animation_right);
        setOutsideTouchable(true);
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
