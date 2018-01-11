package com.bo.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.customview.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class DateZoneChooserView extends LinearLayout implements DatePicker.OnDateChangedListener {
    private Context mContext;
    private TextView mStartTv;
    private TextView mEndTv;
    private DatePicker mDp;
    private boolean isStart;
    private boolean isEnd;
    private LinearLayout mLl;
    private TextView mBtn;
    private final String SHORT_LINE = "-";

    public DateZoneChooserView(Context context) {
        this(context, null);
    }

    public DateZoneChooserView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        View view = LayoutInflater.from(context).inflate(R.layout.date_zone, this, false);
        mStartTv = view.findViewById(R.id.date_zone_start);
        mEndTv = view.findViewById(R.id.date_zone_end);
        mLl = view.findViewById(R.id.date_zone_ll);
        mBtn = view.findViewById(R.id.date_zone_btn);
        {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            mDp = view.findViewById(R.id.date_zone_dp);
            mDp.setMaxDate(System.currentTimeMillis());
            mDp.init(year, month, day, this);
            resizePikcer(mDp);
        }

        {
            setOrientation(VERTICAL);
            addView(view);
        }
        initEvent();
    }

    private void resizePikcer(FrameLayout tp) {
        List<NumberPicker> npList = findNumberPicker(tp);
        for (NumberPicker np : npList) {
            resizeNumberPicker(np);
        }
    }

    private List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
        List<NumberPicker> npList = new ArrayList<NumberPicker>();
        View child = null;
        if (null != viewGroup) {
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                child = viewGroup.getChildAt(i);
                if (child instanceof NumberPicker) {
                    npList.add((NumberPicker) child);
                } else if (child instanceof LinearLayout) {
                    List<NumberPicker> result = findNumberPicker((ViewGroup) child);
                    if (result.size() > 0) {
                        return result;
                    }
                }
            }
        }
        return npList;
    }

    private void resizeNumberPicker(NumberPicker np) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(60, LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 40, 0);
        np.setLayoutParams(params);
    }

    private void initEvent() {
        mStartTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isEnd = false;
                isStart = true;
                mStartTv.setSelected(true);
                mEndTv.setSelected(false);
                mLl.setVisibility(View.VISIBLE);
            }
        });

        mEndTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                isStart = false;
                isEnd = true;
                mStartTv.setSelected(false);
                mEndTv.setSelected(true);
                mLl.setVisibility(View.VISIBLE);
            }
        });
        mBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setDate2Tv();
                mStartTv.setSelected(false);
                mEndTv.setSelected(false);
                mLl.setVisibility(View.GONE);
            }
        });
    }

    private void setDate2Tv() {
        StringBuffer sb = new StringBuffer();
        sb.append(mDp.getYear());
        sb.append(SHORT_LINE);
        sb.append(mDp.getMonth() + 1);
        sb.append(SHORT_LINE);
        sb.append(mDp.getDayOfMonth());
        if (isStart) {
            mStartTv.setText(sb.toString());
        }
        if (isEnd) {
            mEndTv.setText(sb.toString());
        }
    }

    private int startYear, startMonth, startDay;
    private final String ERROR_MSG = "结束日期不能小于开始日期";

    @Override
    public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        if (isStart) {
            mStartTv.setText(year + SHORT_LINE + (monthOfYear + 1) + SHORT_LINE + dayOfMonth);
            Log.e("getDrawingTime"," getDrawingTime ==> "+ view.getDrawingTime());
            startYear = year;
            startMonth = monthOfYear;
            startDay = dayOfMonth;
        }
        if (isEnd) {
//            if (year < startYear) {
//                Toast.makeText(mContext, ERROR_MSG, Toast.LENGTH_SHORT).show();
//                return;
//            }else {
//                if (year == startYear && monthOfYear < startMonth){
//                    Toast.makeText(mContext, ERROR_MSG, Toast.LENGTH_SHORT).show();
//                    return;
//                }else {
//                    if (monthOfYear == startMonth && dayOfMonth < startDay){
//                        Toast.makeText(mContext, ERROR_MSG, Toast.LENGTH_SHORT).show();
//                        return;
//                    }
//                }
//            }
            mEndTv.setText(year + SHORT_LINE + (monthOfYear + 1) + SHORT_LINE + dayOfMonth);
        }
    }

    /**
     * @return 第一个元素是 开始时间, 第二个元素是 结束时间。
     */
    public String[] getDateZone() {
        return new String[]{mStartTv.getText().toString().trim(), mEndTv.getText().toString().trim()};
    }

    public void reset() {
        mStartTv.setText("开始日期");
        mEndTv.setText("结束日期");
    }
}
