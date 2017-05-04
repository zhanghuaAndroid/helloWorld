package com.jiuselu.helloworld.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.jiuselu.helloworld.R;

/**
 * Description: ：
 * Company      : 北京云磅科技
 * Author       :
 * E-Mail       :
 * Date         ：2017/5/4
 * Tell         :
 */
public class WeatherInfoFragment extends Fragment {

    private static final String TAG = "WeatherInfoFragment";
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ScrollView svView;
    private Button btnHome;
    private TextView tvTitle;
    private TextView tvUpdateTime;
    private TextView degreeText;
    private TextView weatherInfoText;
    private LinearLayout hourlyForecastLayout;
    private LinearLayout dailyForecastLayout;
    private TextView airQualityText;
    private TextView aqiText;
    private TextView pm25Text;
    private TextView comfortText;
    private TextView wearText;
    private TextView fluText;
    private TextView carWashText;
    private TextView sportText;
    private TextView travelText;
    private TextView ultravioletRayText;
    private int id;

    public void setId(int id){
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_weather_info, container, false);
        initView();
        return view;
    }

    private void initView() {
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        svView = (ScrollView) view.findViewById(R.id.sv_view);
        btnHome = (Button) view.findViewById(R.id.btn_home);
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        tvUpdateTime = (TextView) view.findViewById(R.id.tv_update_time);
        degreeText = (TextView) view.findViewById(R.id.degree_text);
        weatherInfoText = (TextView) view.findViewById(R.id.weather_info_text);
        hourlyForecastLayout = (LinearLayout) view.findViewById(R.id.hourly_forecast_layout);
        dailyForecastLayout = (LinearLayout) view.findViewById(R.id.daily_forecast_layout);
        airQualityText = (TextView) view.findViewById(R.id.air_quality_text);
        aqiText = (TextView) view.findViewById(R.id.aqi_text);
        pm25Text = (TextView) view.findViewById(R.id.pm25_text);
        comfortText = (TextView) view.findViewById(R.id.comfort_text);
        wearText = (TextView) view.findViewById(R.id.wear_text);
        fluText = (TextView) view.findViewById(R.id.flu_text);
        carWashText = (TextView) view.findViewById(R.id.car_wash_text);
        sportText = (TextView) view.findViewById(R.id.sport_text);
        travelText = (TextView) view.findViewById(R.id.travel_text);
        ultravioletRayText = (TextView) view.findViewById(R.id.ultraviolet_ray_text);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
    }
}
