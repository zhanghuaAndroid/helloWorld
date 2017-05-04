package com.jiuselu.helloworld.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 972694341@qq.com on 2017/4/21.
 */

public class Now {

    @SerializedName("tmp")
    public String temperature;

    @SerializedName("cond")
    public More more;

    public class More{

        @SerializedName("txt")
        public String info;
    }
}
