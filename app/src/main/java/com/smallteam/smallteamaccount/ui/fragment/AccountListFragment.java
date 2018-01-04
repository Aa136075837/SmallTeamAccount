package com.smallteam.smallteamaccount.ui.fragment;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.adapter.AccountAdapter;
import com.smallteam.smallteamaccount.base.MVPBaseFragment;
import com.smallteam.smallteamaccount.base.SmallTeamApp;
import com.smallteam.smallteamaccount.presenter.AccountListContract;
import com.smallteam.smallteamaccount.ui.activity.MainActivity;
import com.smallteam.smallteamaccount.utils.EasyToast;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/1/4.
 */

public class AccountListFragment extends MVPBaseFragment<AccountListContract.AccountListPresenter> implements AccountListContract.AccountListView {
    @BindView(R.id.account_recycle)
    SwipeMenuRecyclerView mAccountRecycle;
    @BindView(R.id.account_refresh)
    SwipeRefreshLayout mAccountRefresh;
    private AccountAdapter mAdapter;

    @Override
    protected void initEvents() {
        mAccountRefresh.setColorSchemeResources(R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
        mAccountRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAccountRefresh.setRefreshing(true);
                mPresenter.getAccountList();
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(SmallTeamApp.getInstance());
        mAccountRecycle.setLayoutManager(manager);
        mAccountRecycle.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                       RecyclerView.State state) {
                outRect.set(10, 10, 10, 10);
            }
        });
        mAccountRecycle.setSwipeMenuCreator(mSwipeMenuCreator);
        mAccountRecycle.setSwipeMenuItemClickListener(mMenuItemClickListener);

        mAdapter = new AccountAdapter(getActivity(), R.layout.item_account);
        mAccountRecycle.setAdapter(mAdapter);
    }

    @Override
    protected void initViews() {

    }

    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridge.closeMenu();
            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                EasyToast.showShort(SmallTeamApp.getInstance(), "删除");
            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
                EasyToast.showShort(SmallTeamApp.getInstance(), "list第" + adapterPosition + "; 左侧菜单第" + menuPosition);
            }
        }
    };

    private SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.top_bar_height);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            SwipeMenuItem deleteItem = new SwipeMenuItem(SmallTeamApp.getInstance());
            deleteItem.setBackground(R.drawable.selector_red)
                .setImage(R.drawable.icon_delete)
                .setWidth(width)
                .setHeight(height);
            swipeRightMenu.addMenuItem(deleteItem);
        }
    };

    @Override
    protected AccountListContract.AccountListPresenter createPresenter() {
        return new AccountListContract.AccountListPresenter(this, getActivity());
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_account_list;
    }

    @Override
    public void getListSuccess() {
        mAccountRefresh.setRefreshing(false);
    }
}
