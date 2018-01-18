package com.smallteam.smallteamaccount.http;

import org.json.JSONObject;

import java.util.LinkedHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by bo on 2018/1/7.
 */
public class RequestBodyUtils {

    private static MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static RequestBody getParams(LinkedHashMap<String, Object> map) {
        JSONObject jsonObject = null;
        if (map != null) {
            jsonObject = new JSONObject(map);
        }else {
            jsonObject = new JSONObject();
        }
        return RequestBody.create(JSON, jsonObject.toString());
    }

}
