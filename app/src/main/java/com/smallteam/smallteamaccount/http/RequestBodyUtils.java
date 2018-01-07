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

    private static LinkedHashMap<String, Object> getDefaultParams() {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();

        return map;
    }

    public static RequestBody getParams(LinkedHashMap<String, Object> map) {
        LinkedHashMap<String, Object> defaultParams = getDefaultParams();
        if (map != null) {
            defaultParams.putAll(map);
        }
        JSONObject jsonObject = new JSONObject(defaultParams);
        return RequestBody.create(JSON, jsonObject.toString());
    }

}
