package com.smallteam.smallteamaccount.presenter;

import android.content.Context;
import android.media.JetPlayer;

import com.smallteam.smallteamaccount.base.BasePresenter;
import com.smallteam.smallteamaccount.base.BaseView;
import com.smallteam.smallteamaccount.bean.NewAccountBean;
import com.smallteam.smallteamaccount.http.HttpObserver;
import com.smallteam.smallteamaccount.http.Load;
import com.smallteam.smallteamaccount.http.RequestBodyUtils;
import com.smallteam.smallteamaccount.utils.SPUtils;
import com.smallteam.smallteamaccount.utils.SpConfig;

import java.util.LinkedHashMap;

import okhttp3.RequestBody;

/**
 * Created by TENGFEI on 2018/1/18.
 */
public interface CreateBooksAccountContract {

    interface CreateAccountView extends BaseView{

    }

     class CreateBooksAccountPresenter extends BasePresenter<CreateAccountView>{

         public CreateBooksAccountPresenter(CreateAccountView view, Context context) {
             super(view, context);
         }

         /**
          * 新增账本
          */
        public void addOneBookAccount(String name,String description){

            String id = SpConfig.getInstance().getUser().getId();

            LinkedHashMap<String,Object> map = new LinkedHashMap<>();
            map.put("creater",id);
            map.put("name",name);
            map.put("description",description);
            RequestBody body = RequestBodyUtils.getParams(map);

            HttpObserver<NewAccountBean> httpObserver = Load.call(Load.createApi().addOneAccount(body)).
                subscribeWith(new HttpObserver<NewAccountBean>(mView,true) {
                @Override
                protected void call(NewAccountBean value) {
                    /*创建成功*/

                }
            });

            addObservable(httpObserver);
        }
     }
}
