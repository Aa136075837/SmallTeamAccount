package com.smallteam.smallteamaccount.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.base.MVPBaseActivity;
import com.smallteam.smallteamaccount.bean.NewAccountBean;
import com.smallteam.smallteamaccount.presenter.AddGroupContract;
import com.smallteam.smallteamaccount.utils.EasyToast;
import com.smallteam.smallteamaccount.utils.SpConfig;

import java.util.LinkedHashMap;

import butterknife.BindView;

/**
 * @author Macx
 */
public class AddGroupActivity extends MVPBaseActivity<AddGroupContract.AddGroupPresenter>
        implements AddGroupContract.AddGroupView {

    @BindView(R.id.et_group_name)
    EditText mEtGroupName;
    @BindView(R.id.et_group_desc)
    EditText mEtGroupDesc;
    @BindView(R.id.tv_group_create)
    TextView mTvGroupCreate;

    @Override
    protected int initLayout() {
        return R.layout.activity_add_group;
    }

    @Override
    protected void initParams(Bundle bundle) {

    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initEvents() {
        mTvGroupCreate.setOnClickListener(v -> {
            LinkedHashMap<String, Object> map = getAddGroupParams();
            if (map == null) {
                return;
            }
            mPresenter.addOneAccount(map);
        });
    }

    @NonNull
    private LinkedHashMap<String, Object> getAddGroupParams() {
        String groupName = mEtGroupName.getText().toString().trim();
        String groupDesc = mEtGroupDesc.getText().toString().trim();

        if (TextUtils.isEmpty(groupDesc) || TextUtils.isEmpty(groupName)) {
            EasyToast.showShort(this,"组名或者描述不能为空");
            return null;
        }

        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("name", groupName);
        map.put("creater", SpConfig.getInstance().getUser().getId());
        map.put("description", groupDesc);
        return map;

    }

    @Override
    protected AddGroupContract.AddGroupPresenter createPresenter() {
        return new AddGroupContract.AddGroupPresenter(this, this);
    }

    @Override
    public void addAccountSuccess(NewAccountBean value) {
        finish();
    }

}
