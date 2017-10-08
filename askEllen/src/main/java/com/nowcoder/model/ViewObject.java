package com.nowcoder.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//用于汇总信息输出到页面
public class ViewObject {
    private Map<String,Object> objs = new HashMap<>();

    public void set(String key,Object value){
        objs.put(key,value);
    }

    public Object get(String key){
        return objs.get(key);
    }
}
