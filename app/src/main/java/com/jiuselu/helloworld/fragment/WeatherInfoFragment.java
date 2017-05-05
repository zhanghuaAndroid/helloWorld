package com.jiuselu.helloworld.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jiuselu.helloworld.R;
import com.jiuselu.helloworld.activity.WeatherActivity;
import com.jiuselu.helloworld.bean.WeatherInfo;
import com.jiuselu.helloworld.ui.UIUtils;
import com.jiuselu.helloworld.utils.HttpUtil;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

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
    public DrawerLayout drawerLayout;
    private String weatherId;
    private TextView pm10Text;
    private TextView dirText;
    private TextView scText;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        weatherId = getActivity().getIntent().getStringExtra("weatherId");

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
        drawerLayout = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        pm10Text = (TextView) view.findViewById(R.id.pm10_text);
        dirText = (TextView) view.findViewById(R.id.dir_text);
        scText = (TextView) view.findViewById(R.id.sc_text);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((WeatherActivity) getActivity()).drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        requestWeather(weatherId);

        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimaryDark);

        //下拉刷新
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                requestWeather(weatherId);
            }
        });
    }

    /**
     * 请求网络获取天气信息
     *
     * @param weatherId
     */
    private void requestWeather(final String weatherId) {
        String WeatherUrl = "http://guolin.tech/api/weather?cityid=" + weatherId + "&key=4001c9cd6c37490e8d0fbbc899b7c8d0";
        HttpUtil.sendOkHttpRequest(WeatherUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure:   失败" + e);
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null) {
                    String weatherInfo = response.body().string();
                    //Log.d(TAG, "onResponse: 天气信息：" + weatherInfo);
                    Gson gson = new Gson();
                    WeatherInfo weatherInfos = gson.fromJson(weatherInfo, WeatherInfo.class);
                    List<WeatherInfo.HeWeatherBean> heWeather = weatherInfos.getHeWeather();
                    WeatherInfo.HeWeatherBean heWeatherBean = heWeather.get(0);
                    final WeatherInfo.HeWeatherBean.AqiBean aqi = heWeatherBean.getAqi();
                    final WeatherInfo.HeWeatherBean.BasicBean basic = heWeatherBean.getBasic();
                    final List<WeatherInfo.HeWeatherBean.DailyForecastBean> dailyForecast = heWeatherBean.getDaily_forecast();

                    List<WeatherInfo.HeWeatherBean.HourlyForecastBean> hourlyForecast = heWeatherBean.getHourly_forecast();
                    WeatherInfo.HeWeatherBean.NowBean now = heWeatherBean.getNow();
                    final WeatherInfo.HeWeatherBean.SuggestionBean suggestion = heWeatherBean.getSuggestion();
                    String status = heWeatherBean.getStatus();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            swipeRefreshLayout.setRefreshing(false);
                            Log.d(TAG, "run: 集合热：" + dailyForecast.size());
                            tvTitle.setText(basic.getCity());
                            degreeText.setText(dailyForecast.get(0).getTmp().getMax() + "℃");
                            weatherInfoText.setText(dailyForecast.get(0).getCond().getTxt_d());
                            dirText.setText(dailyForecast.get(0).getWind().getDir());
                            scText.setText(dailyForecast.get(0).getWind().getSc() + "级");

                            View view = View.inflate(UIUtils.getContext(), R.layout.hourly_forecast_item, null);
                            TextView hourlyForecastItemText = (TextView) view.findViewById(R.id.hourly_forecast_time_text);
                            //hourlyForecastItemText.setText();
                            hourlyForecastLayout.addView(view);
                            airQualityText.setText(aqi.getCity().getQlty());
                            aqiText.setText(aqi.getCity().getAqi());
                            pm25Text.setText(aqi.getCity().getPm25());
                            pm10Text.setText(aqi.getCity().getPm10());
                            tvUpdateTime.setText(basic.getUpdate().getLoc());
                            comfortText.setText(suggestion.getComf().getTxt());
                            wearText.setText(suggestion.getTrav().getTxt());
                            fluText.setText(suggestion.getFlu().getTxt());
                            carWashText.setText(suggestion.getCw().getTxt());
                            sportText.setText(suggestion.getSport().getTxt());
                            travelText.setText(suggestion.getDrsg().getTxt());
                            ultravioletRayText.setText(suggestion.getUv().getTxt());
                        }
                    });
                }
            }
        });
    }
}
