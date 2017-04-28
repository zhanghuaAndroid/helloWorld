package com.jiuselu.helloworld.utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Description: ：
 * Company      :
 * Author       :
 * E-Mail       :
 * Date         ：2017/4/28
 * Tell         :
 */
public class HttpUtil {

    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        okHttpClient.newCall(request).enqueue(callback);

    }

}
