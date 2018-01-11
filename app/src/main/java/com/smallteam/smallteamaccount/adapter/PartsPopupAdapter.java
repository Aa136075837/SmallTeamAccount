package com.smallteam.smallteamaccount.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.SmallTeamApp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class PartsPopupAdapter extends BaseAdapter {
    List<String> mData;
    List<String> mCheckedName = new ArrayList<>();

    public PartsPopupAdapter(List<String> data) {
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PartsHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(SmallTeamApp.getInstance()).inflate(R.layout.item_parts_popup, parent, false);
            holder = new PartsHolder();
            holder.mLayout = convertView.findViewById(R.id.item_part_ll);
            holder.mTextView = convertView.findViewById(R.id.item_part_tv);
            holder.mCheckBox = convertView.findViewById(R.id.item_part_cb);
            convertView.setTag(holder);
        } else {
            holder = (PartsHolder) convertView.getTag();
        }
        String name = mData.get(position);
        holder.mTextView.setText(name);
        PartsHolder finalHolder = holder;
        holder.mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalHolder.mCheckBox.setChecked(!finalHolder.mCheckBox.isChecked());
                if (finalHolder.mCheckBox.isChecked() && !mCheckedName.contains(name)){
                    mCheckedName.add(name);
                }else{
                    mCheckedName.remove(name);
                }
            }
        });
        return convertView;
    }

    public List<String> getCheckedName(){
        return mCheckedName;
    }

    static class PartsHolder {
        TextView mTextView;
        CheckBox mCheckBox;
        LinearLayout mLayout;
    }
}
