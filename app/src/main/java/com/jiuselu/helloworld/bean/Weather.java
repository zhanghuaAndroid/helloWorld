package com.jiuselu.helloworld.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by 972694341@qq.com on 2017/4/21.
 */

public class Weather {

    public String status;

    public Basic basic;

    public AQI aqi;

    public Now now;

    public Suggestion suggestion;

    @SerializedName("daily_forecast")
    public List<DailyForecast> dailyForecastList;

    @SerializedName("hourly_forecast")
    public List<HourlyForecast> hourlyForecasts;
}
