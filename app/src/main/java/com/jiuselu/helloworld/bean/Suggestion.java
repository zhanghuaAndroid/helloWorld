package com.jiuselu.helloworld.bean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by 972694341@qq.com on 2017/4/21.
 */

public class Suggestion {

    @SerializedName("comf")
    public Comfort comfort;

    public Flu flu;

    @SerializedName("cw")
    public CarWash carWash;

    public Sport sport;

    @SerializedName("trav")
    public Travel travel;

    @SerializedName("uv")
    public UltravioletRay ultravioletRay;

    @SerializedName("drsg")
    public Wear wear;

    public class Comfort{

        public String brf;

        @SerializedName("txt")
        public String info;

    }

    public class Flu{
        public String brf;

        @SerializedName("txt")
        public String info;
    }

    public class CarWash{

        public String brf;

        @SerializedName("txt")
        public String info;

    }

    public class Sport{

        public String brf;

        @SerializedName("txt")
        public String info;
    }

    public class Travel{

        public String brf;

        @SerializedName("txt")
        public String info;
    }

    public class UltravioletRay{

        public String brf;

        @SerializedName("txt")
        public String info;
    }

    public class Wear{

        public String brf;

        @SerializedName("txt")
        public String info;
    }
}
