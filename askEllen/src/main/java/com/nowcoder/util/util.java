package com.nowcoder.util;

import com.alibaba.fastjson.JSONObject;

public class util {

    public static String getJsonString(int code,String msg){
        JSONObject json = new JSONObject();
        json.put("code",code);
        json.put("msg",msg);
        return json.toJSONString();
    }


    public static String getJsonString(int code){
        JSONObject json = new JSONObject();
        json.put("code",code);
        return json.toJSONString();
    }
}
