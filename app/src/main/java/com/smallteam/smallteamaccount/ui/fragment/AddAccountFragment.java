package com.smallteam.smallteamaccount.ui.fragment;

import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.MVPBaseFragment;
import com.smallteam.smallteamaccount.presenter.AddAccountContract;
import com.smallteam.smallteamaccount.ui.view.DateChooserPopup;
import com.smallteam.smallteamaccount.ui.view.PartsPopupWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AddAccountFragment extends MVPBaseFragment<AddAccountContract.AddAccountPresenter>
        implements AddAccountContract.AddAccountView, DateChooserPopup.PopupDateChangedListener,
        PartsPopupWindow.PartsCheckNameChangedListener {

    @BindView(R.id.add_money_et)
    EditText mAddMoneyEt;
    @BindView(R.id.add_date_et)
    TextInputEditText mAddDateEt;
    @BindView(R.id.add_types_et)
    TextInputEditText mAddTypesEt;
    @BindView(R.id.add_parts_et)
    TextInputEditText mAddPartsEt;
    @BindView(R.id.textlayout)
    TextInputLayout mtextlayout;
    @BindView(R.id.add_grid_all)
    TextView mAddGridAll;
    @BindView(R.id.add_grid_daily)
    TextView mAddGridDaily;
    @BindView(R.id.add_grid_hotel)
    TextView mAddGridHotel;
    @BindView(R.id.add_grid_travel)
    TextView mAddGridTravel;
    @BindView(R.id.add_grid_entertain)
    TextView mAddGridEntertain;
    @BindView(R.id.add_grid_other)
    TextView mAddGridOther;
    @BindView(R.id.add_grid_layout)
    GridLayout mAddGridLayout;

    @Override
    protected void initEvents() {
        mAddDateEt.setOnClickListener(v -> {
            DateChooserPopup dateChooserPopup = new DateChooserPopup(getActivity());
            dateChooserPopup.showAsDropDown(mAddDateEt, -20, 0);
            dateChooserPopup.setPopupDateChangedListener(this);
        });
        mAddPartsEt.setOnClickListener(view -> {
            List<String> list = new ArrayList<>();
            list.add("q");
            list.add("w");
            list.add("e");
            list.add("r");
            list.add("d");
            list.add("f");
            PartsPopupWindow p = new PartsPopupWindow(getActivity(), list);
            p.setPartsCheckNameChangedListener(this);
            p.showAsDropDown(mAddPartsEt, 50, 0);
        });
        mAddTypesEt.setOnClickListener(view -> {
            mAddGridLayout.setVisibility(View.VISIBLE);
        });
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected AddAccountContract.AddAccountPresenter createPresenter() {
        return new AddAccountContract.AddAccountPresenter(this, getActivity());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_add_account;
    }

    @Override
    public void dataChanged(int year1, int monthOfYear, int dayOfMonth) {

    }

    @Override
    public void dataChanged(String s) {
        mAddDateEt.setText(s);
    }

    @Override
    public void onCheckNameChanged(List<String> checkedName) {
        mAddPartsEt.setText(checkedName.toString());
    }

    @OnClick({R.id.add_grid_all, R.id.add_grid_daily, R.id.add_grid_hotel,
            R.id.add_grid_travel, R.id.add_grid_entertain, R.id.add_grid_other})

    public void onClick(View view) {
        String content = "";
        switch (view.getId()) {
            case R.id.add_grid_all:
                content = mAddGridAll.getText().toString().trim();
                break;
            case R.id.add_grid_daily:
                content = mAddGridDaily.getText().toString().trim();
                break;
            case R.id.add_grid_hotel:
                content = mAddGridHotel.getText().toString().trim();
                break;
            case R.id.add_grid_travel:
                content = mAddGridTravel.getText().toString().trim();
                break;
            case R.id.add_grid_entertain:
                content = mAddGridEntertain.getText().toString().trim();
                break;
            case R.id.add_grid_other:
                content = mAddGridOther.getText().toString().trim();
                break;
        }
        mAddTypesEt.setText(content);
        mAddGridLayout.setVisibility(View.GONE);
    }
}
