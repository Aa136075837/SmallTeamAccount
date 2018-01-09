package com.smallteam.smallteamaccount.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.SmallTeamApp;
import com.smallteam.smallteamaccount.utils.L;

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
            holder.mTextView = convertView.findViewById(R.id.item_part_tv);
            holder.mCheckBox = convertView.findViewById(R.id.item_part_cb);
            convertView.setTag(holder);
        } else {
            holder = (PartsHolder) convertView.getTag();
        }
        String name = mData.get(position);
        holder.mTextView.setText(name);
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && !mCheckedName.contains(name)){
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
    }
}
