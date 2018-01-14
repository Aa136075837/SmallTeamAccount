package com.smallteam.smallteamaccount.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bo.customview.utils.DatePickerUtils;
import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.utils.DisplayUtil;

import java.util.Calendar;

/**
 * Created by Administrator on 2018/1/12.
 */

public class DateChooserPopup extends PopupWindow {

    private final DatePicker mDP;
    private final TextView mOk;

    public DateChooserPopup(Context context) {
        super(context);
        View view = LayoutInflater.from(context).inflate(R.layout.date_chooser_popup, null);

        mDP = view.findViewById(R.id.date_popup_dp);
        mOk = view.findViewById(R.id.date_popup_tv);
        mDP.setMaxDate(System.currentTimeMillis());
        DatePickerUtils.resizePicker(mDP);


        setContentView(view);
        initEvent();
//        setBackgroundDrawable(null);
        setHeight(DisplayUtil.dip2px(context, 260));
        setWidth(DisplayUtil.dip2px(context, 198));
        setFocusable(true);
        setAnimationStyle(R.style.popupWindow_animation_top);
        setOutsideTouchable(true);
    }

    private void initEvent() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        mDP.init(year, month, day, (datePicker, year1, monthOfYear, dayOfMonth) -> {
            if (mListener != null) {
                StringBuffer sb = new StringBuffer();
                sb.append(year1).append("-").append(monthOfYear+1).append("-").append(dayOfMonth);
                mListener.dataChanged(sb.toString());
            }
        });

        mOk.setOnClickListener(view -> {
            if (mListener != null) {
                StringBuffer sb = new StringBuffer();
                sb.append(mDP.getYear()).append("-").append(mDP.getMonth()+1).append("-").append(mDP.getDayOfMonth());
                mListener.dataChanged(sb.toString());
                dismiss();
            }
        });
    }

    private PopupDateChangedListener mListener;

    public void setPopupDateChangedListener(PopupDateChangedListener listener) {
        mListener = listener;
    }

    public interface PopupDateChangedListener {
        void dataChanged(int year1, int monthOfYear, int dayOfMonth);

        void dataChanged(String s);
    }
}
