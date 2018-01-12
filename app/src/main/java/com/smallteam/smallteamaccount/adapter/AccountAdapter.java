package com.smallteam.smallteamaccount.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.smallteam.smallteamaccount.R;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AccountAdapter extends RecyclerView.Adapter<AccountAdapter.AccountHolder> {
    private Context mContext;
    private int mResId;

    public AccountAdapter(Context context, @LayoutRes int resId) {
        super();
        mContext = context;
        mResId = resId;
    }

    @Override
    public AccountHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(mResId, parent, false);
        return new AccountHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountHolder holder, int position) {
        holder.itemView.setTag(position);
        if (position % 2 == 0) {
            holder.mLeftView.setBackground(mContext.getResources().getDrawable(R.drawable.item_left_blue));
        }else{
            holder.mLeftView.setBackground(mContext.getResources().getDrawable(R.drawable.item_left_orange));
        }

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    static class AccountHolder extends RecyclerView.ViewHolder {

        private final View mLeftView;
        private final TextView mPayerTv;
        private final TextView mDate;
        private final TextView mMoney;

        public AccountHolder(View itemView) {
            super(itemView);
            mLeftView = itemView.findViewById(R.id.item_left_view);
            mPayerTv = itemView.findViewById(R.id.item_payer);
            mDate = itemView.findViewById(R.id.item_date);
            mMoney = itemView.findViewById(R.id.item_money);
        }
    }
}