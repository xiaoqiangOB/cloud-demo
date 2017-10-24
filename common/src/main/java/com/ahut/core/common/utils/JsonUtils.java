package com.ahut.core.common.utils;

import com.google.gson.Gson;

/**
 * Created by c2292 on 2017/10/23.
 */
public class JsonUtils {
    public static String toJsonString(Object obj){
        Gson gson = new Gson();
        return gson.toJson(obj);
    }
    @SuppressWarnings("unchecked")//告诉编译器忽略指定的警告，不用在编译完成后出现警告信息
    public static <T> T strToJson(String str, Class<?> clazz){
        Gson gson = new Gson();
        return (T) gson.fromJson(str,clazz);

    }
}
