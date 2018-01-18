package com.smallteam.smallteamaccount.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.bean.GroupBean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class DrawerTabAdapter extends RecyclerView.Adapter<DrawerTabAdapter.DrawerHolder> {

    private Context mContext;
    private List<GroupBean> mData;

    public DrawerTabAdapter(Context context) {
        mContext = context;
    }

    public void setData(List<GroupBean> mData) {
        this.mData = mData;
    }

    @Override
    public DrawerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_tab, parent, false);
        return new DrawerHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerHolder holder, int position) {
        holder.mGroupNameTv.setText(mData.get(position).getName());
        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onDrawerItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData==null?0:mData.size();
    }

    private DrawerItemClickListener mListener;

    public void setDrawerItemClickListener(DrawerItemClickListener listener) {
        mListener = listener;
    }

    public interface DrawerItemClickListener {
        void onDrawerItemClick(int position);
    }

    static class DrawerHolder extends RecyclerView.ViewHolder {

        private final ImageView mGroupIconIv;
        private final TextView mGroupNameTv;

        public DrawerHolder(View itemView) {
            super(itemView);
            mGroupIconIv = (ImageView) itemView.findViewById(R.id.item_main_tab_iv);
            mGroupNameTv = (TextView) itemView.findViewById(R.id.item_main_tab_tv);
        }
    }
}
