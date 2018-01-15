package com.smallteam.smallteamaccount.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smallteam.smallteamaccount.R;

import java.util.List;

/**
 * Created by Administrator on 2018/1/15.
 */

public class DrawerTabAdapter extends RecyclerView.Adapter<DrawerTabAdapter.DrawerHolder> {

    private Context mContext;
    private List<String> mData;

    public DrawerTabAdapter(Context context, List<String> data) {
        mContext = context;
        mData = data;
    }

    @Override
    public DrawerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_main_tab, parent, false);
        return new DrawerHolder(view);
    }

    @Override
    public void onBindViewHolder(DrawerHolder holder, int position) {
        holder.mTabTv.setText(mData.get(position));
        holder.itemView.setOnClickListener(v -> {
            if (mListener != null) {
                mListener.onDrawerItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    private DrawerItemClickListener mListener;

    public void setDrawerItemClickListener(DrawerItemClickListener listener) {
        mListener = listener;
    }

    public interface DrawerItemClickListener {
        void onDrawerItemClick(int position);
    }

    static class DrawerHolder extends RecyclerView.ViewHolder {

        private final ImageView mTabIv;
        private final TextView mTabTv;

        public DrawerHolder(View itemView) {
            super(itemView);
            mTabIv = (ImageView) itemView.findViewById(R.id.item_main_tab_iv);
            mTabTv = (TextView) itemView.findViewById(R.id.item_main_tab_tv);
        }
    }
}
