package com.smallteam.smallteamaccount.ui.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.MVPBaseFragment;
import com.smallteam.smallteamaccount.presenter.UserBalanceContract;
import com.smallteam.smallteamaccount.utils.L;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/4.
 */

public class UserBalanceFragment extends MVPBaseFragment<UserBalanceContract.UserBalancePresenter> implements UserBalanceContract.UserBalanceView, OnChartValueSelectedListener {
    @BindView(R.id.balance_pie)
    PieChart mBalancePie;
    @BindView(R.id.balance_change_hole)
    TextView mBalanceChangeHole;

    @Override
    protected void initViews() {
        mBalancePie.setUsePercentValues(true);
        mBalancePie.getDescription().setEnabled(false);

        mBalancePie.setDragDecelerationFrictionCoef(0.95f);

        mBalancePie.setCenterText("个人收支");

        mBalancePie.setExtraOffsets(10.f, 0.f, 38.f, 0.f);

        mBalancePie.setDrawHoleEnabled(true);
        mBalancePie.setHoleColor(Color.WHITE);

        mBalancePie.setTransparentCircleColor(Color.BLACK);
        mBalancePie.setTransparentCircleAlpha(110);

        mBalancePie.setHoleRadius(58f);
        mBalancePie.setTransparentCircleRadius(61f);

        mBalancePie.setDrawCenterText(true);

        mBalancePie.setRotationAngle(0);
        // enable rotation of the chart by touch
        mBalancePie.setRotationEnabled(true);
        mBalancePie.setHighlightPerTapEnabled(true);

        mBalancePie.setDrawEntryLabels(false);

        // mChart.setUnit(" €");
        // mChart.setDrawUnitsInChart(true);

        // add a selection listener
        mBalancePie.setOnChartValueSelectedListener(this);

        setData(5, 100);

        mBalancePie.animateY(1400, Easing.EasingOption.EaseInOutQuad);

        Legend l = mBalancePie.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setXEntrySpace(0f);
        l.setYEntrySpace(0f);
        l.setYOffset(10f);

        mBalancePie.setEntryLabelColor(Color.WHITE);
        mBalancePie.setEntryLabelTextSize(12f);
    }

    @Override
    protected void initEvents() {
        mBalanceChangeHole.setOnClickListener(view -> {
                mBalancePie.setDrawHoleEnabled(!mBalancePie.isDrawHoleEnabled());
                mBalancePie.invalidate();
            }
        );
    }

    @Override
    protected UserBalanceContract.UserBalancePresenter createPresenter() {
        return new UserBalanceContract.UserBalancePresenter(this, getActivity());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_user_balance;
    }

    protected String[] mParties = new String[]{
        "生活用品","住店","出行","文化娱乐","其他",
    };
    protected String[] moneys = new String[]{
        "451.23元","1321元","236.01元","586.15元","659元",
    };

    private void setData(int count, float range) {

        float mult = range;

        ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < count; i++) {
            entries.add(new PieEntry((float) ((Math.random() * mult) + mult / 5),
                mParties[i % mParties.length] +": "+ moneys[i % moneys.length],
                getResources().getDrawable(R.drawable.account_fill_red),"1231"));
        }

        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(1f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(3f);

        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        //dataSet.setSelectionShift(0f);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.WHITE);
//        data.setValueTypeface(mTfLight);
        mBalancePie.setData(data);

        // undo all highlights
        mBalancePie.highlightValues(null);

        mBalancePie.invalidate();
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        if(e != null){
            Object data = e.getData();
            if(data != null){
                L.d(e.toString()+ "  " + data.toString());
            }else{
                L.d(e.toString());
            }
        }
    }

    @Override
    public void onNothingSelected() {

    }
}
