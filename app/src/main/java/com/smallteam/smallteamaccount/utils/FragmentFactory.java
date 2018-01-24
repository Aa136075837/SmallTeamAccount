package com.smallteam.smallteamaccount.utils;

import android.util.SparseArray;

import com.smallteam.smallteamaccount.base.BaseFragment;
import com.smallteam.smallteamaccount.ui.fragment.AccountListFragment;
import com.smallteam.smallteamaccount.ui.fragment.AddAccountFragment;
import com.smallteam.smallteamaccount.ui.fragment.UserBalanceFragment;

/**
 * Created by Administrator on 2018/1/4.
 */

public class FragmentFactory {
    private static SparseArray<BaseFragment> fragments = new SparseArray<>();

    public static BaseFragment create(int index) {
        BaseFragment fragment = fragments.get(index);
        if (fragment == null) {
            switch (index) {
                case 0:
                    fragment = new AccountListFragment();
                    break;
                case 1:
                    fragment = new AddAccountFragment();
                    break;
                case 2:
                    fragment = new UserBalanceFragment();
                    break;
                default:
                    break;
            }
            if (fragment != null) {
                fragments.put(index, fragment);
            }
        }
        return fragment;
    }
}
