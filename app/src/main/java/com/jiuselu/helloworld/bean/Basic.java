package com.jiuselu.helloworld.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 972694341@qq.com on 2017/4/21.
 */

public class Basic {

    @SerializedName("city")
    public String cityName;

    @SerializedName("id")
    public String weatherId;

    public Update update;

    public class Update{

        @SerializedName("loc")
        public String updateTime;

    }
}
