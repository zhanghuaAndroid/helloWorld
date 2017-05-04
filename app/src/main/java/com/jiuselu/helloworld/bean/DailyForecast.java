package com.jiuselu.helloworld.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 972694341@qq.com on 2017/4/21.
 */

public class DailyForecast {

    public String date;

    @SerializedName("tmp")
    public Temperature temperature;

    @SerializedName("cond")
    public More more;

    public class Temperature{

        public String max;

        public String min;

    }

    public class More{

        @SerializedName("txt_d")
        public String info;

    }
}
