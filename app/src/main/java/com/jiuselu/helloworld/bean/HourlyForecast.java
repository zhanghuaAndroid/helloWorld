package com.jiuselu.helloworld.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 972694341@qq.com on 2017/4/25.
 */

public class HourlyForecast {

    @SerializedName("cond")
    public Condition condition;

    public String date;

    @SerializedName("tmp")
    public String temperature;

    public class Condition{

        @SerializedName("txt")
        public String state;
    }
}
