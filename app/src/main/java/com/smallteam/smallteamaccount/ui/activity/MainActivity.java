package com.smallteam.smallteamaccount.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.adapter.DrawerTabAdapter;
import com.smallteam.smallteamaccount.base.MVPBaseActivity;
import com.smallteam.smallteamaccount.bean.GroupBean;
import com.smallteam.smallteamaccount.presenter.MainContract;
import com.smallteam.smallteamaccount.ui.fragment.AccountListFragment;
import com.smallteam.smallteamaccount.ui.fragment.AddAccountFragment;
import com.smallteam.smallteamaccount.ui.fragment.UserBalanceFragment;
import com.smallteam.smallteamaccount.utils.EasyToast;
import com.smallteam.smallteamaccount.utils.FragmentFactory;

import java.util.List;

/**
 * TODO 获取到默认账本的名称；获取用户有哪些账本
 *
 * @author Macx
 */
public class MainActivity extends MVPBaseActivity<MainContract.MainPresenter>
        implements MainContract.MainView, DrawerTabAdapter.DrawerItemClickListener {

    private AccountListFragment mAccountListFragment;
    private Toolbar mToolbar;
    private AddAccountFragment mAddAccountFragment;
    private UserBalanceFragment mUserBalanceFragment;
    private BottomNavigationView mBnv;
    private RecyclerView mRv;
    private DrawerLayout mDrawer;
    private DrawerTabAdapter tabAdapter;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    protected void initViews() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mBnv = findViewById(R.id.bnv_main);
        mRv = findViewById(R.id.main_tab_rv);

        initTab();
        mDrawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawer.addDrawerListener(toggle);
        toggle.syncState();

        findViewById(R.id.tv_add_account).setOnClickListener(v -> {
            /*添加账本*/
            mPresenter.addOneAccount();
        });

        /*请求所有账本*/
        mPresenter.getGroups();

        /**默认进入账单列表页面*/
        toAccountListFragment();
    }

    private void initTab() {
        mBnv.setOnNavigationItemSelectedListener(item -> {
            int itemId = item.getItemId();
            item.setChecked(true);
            switch (itemId) {
                case R.id.account_list:
                    toAccountListFragment();
                    break;
                case R.id.account_balance:
                    toUserBalanceFragment();
                    break;
                case R.id.account_add:
                    toAddAccountFragment();
                    break;
                default:
                    break;
            }
            return false;
        });

        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRv.setLayoutManager(manager);
        tabAdapter = new DrawerTabAdapter(this);
        tabAdapter.setDrawerItemClickListener(this);
        mRv.setAdapter(tabAdapter);
    }

    @Override
    protected void initEvents() {

        findViewById(R.id.main_tab_setting_ll).setOnClickListener(v -> {
            comming();
            mDrawer.closeDrawers();
        });

        findViewById(R.id.main_tab_out_ll).setOnClickListener(v -> {
            comming();
            mDrawer.closeDrawers();
        });
    }

    @Override
    public void onDrawerItemClick(int position) {
        comming();
        mDrawer.closeDrawers();
    }

    @Override
    protected MainContract.MainPresenter createPresenter() {
        return new MainContract.MainPresenter(this, this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    private void comming() {
        EasyToast.showShort(this, "正在开发，敬请期待....");
    }

    /**
     * 切换到个人收支页面
     */
    private void toUserBalanceFragment() {
        if (mUserBalanceFragment == null) {
            mUserBalanceFragment = (UserBalanceFragment) FragmentFactory.create(2);
        }
        onSwitchFragment(mUserBalanceFragment);
        mToolbar.setTitle(R.string.user_balance);
    }

    /**
     * 切换到添加账单页面
     */
    private void toAddAccountFragment() {
        if (mAddAccountFragment == null) {
            mAddAccountFragment = (AddAccountFragment) FragmentFactory.create(1);
        }
        onSwitchFragment(mAddAccountFragment);
        mToolbar.setTitle(R.string.add_account);
    }

    /**
     * 切换到账单列表页面
     */
    private void toAccountListFragment() {
        if (mAccountListFragment == null) {
            mAccountListFragment = (AccountListFragment) FragmentFactory.create(0);
        }
        onSwitchFragment(mAccountListFragment);
        mToolbar.setTitle(R.string.account_list);
    }

    @Override
    public void textSuccess() {
        Snackbar.make(mBnv, " 测试请求成功...", Snackbar.LENGTH_LONG).setAction("duai", view -> {

        }).show();
    }

    /**
     * 处理返回的所有账本
     */
    @Override
    public void setGroupList(List<GroupBean> groupBeans) {
        tabAdapter.setData(groupBeans);
        tabAdapter.notifyDataSetChanged();
    }

    private long firstTime = 0;
    private final int BACK_PRESS_SPACE = 2000;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (System.currentTimeMillis() - firstTime > BACK_PRESS_SPACE) {
                EasyToast.showShort(MainActivity.this, "再按一次退出程序");
                firstTime = System.currentTimeMillis();
            } else {
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
