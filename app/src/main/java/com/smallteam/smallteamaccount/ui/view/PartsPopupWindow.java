package com.smallteam.smallteamaccount.ui.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.smallteam.smallteamaccount.R;
import com.smallteam.smallteamaccount.adapter.PartsPopupAdapter;
import com.smallteam.smallteamaccount.base.SmallTeamApp;
import com.smallteam.smallteamaccount.utils.DisplayUtil;
import com.smallteam.smallteamaccount.utils.EasyToast;
import com.smallteam.smallteamaccount.utils.L;

import java.util.List;

/**
 * Created by Administrator on 2018/1/9.
 */

public class PartsPopupWindow extends PopupWindow {

    public PartsPopupWindow(Context context, List<String> list) {
        super(context);
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int height = manager.getDefaultDisplay().getHeight();

        View view = LayoutInflater.from(context).inflate(R.layout.parts_popup, null);
        ListView listView = view.findViewById(R.id.parts_popup_lv);
        TextView btn = view.findViewById(R.id.parts_popup_btn);
        PartsPopupAdapter adapter = new PartsPopupAdapter(list);
        listView.setAdapter(adapter);
        btn.setOnClickListener(v -> getCheckedName(adapter));

        setContentView(view);
        setBackgroundDrawable(null);
        setHeight(DisplayUtil.dip2px(context, 320));
        setWidth(DisplayUtil.dip2px(context, 198));
        setFocusable(true);
        setAnimationStyle(R.style.popupWindow_animation_top);
        setOutsideTouchable(true);
    }

    private void getCheckedName(PartsPopupAdapter adapter) {
        List<String> checkedName = adapter.getCheckedName();
        EasyToast.showShort(SmallTeamApp.getInstance(), " 选中 ：：" + checkedName.size() + " 个");
        if (mListener != null){
            mListener.onCheckNameChanged(checkedName);
        }
        dismiss();
    }

    public void showRight(View v) {
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        showAtLocation(v, Gravity.NO_GRAVITY, location[0] + v.getWidth(), location[1]);
    }

    private PartsCheckNameChangedListener mListener;

    public void setPartsCheckNameChangedListener(PartsCheckNameChangedListener l) {
        if (l != null){
            mListener = l;
        }
    }

    public interface PartsCheckNameChangedListener {
        void onCheckNameChanged(List<String> checkedName);
    }
}
