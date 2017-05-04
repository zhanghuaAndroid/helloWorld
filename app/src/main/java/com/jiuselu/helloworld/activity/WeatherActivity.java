package com.jiuselu.helloworld.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.jiuselu.helloworld.R;
import com.jiuselu.helloworld.adapter.WeatherInfoAdapter;
import com.jiuselu.helloworld.fragment.ChooseAreaFragment;
import com.jiuselu.helloworld.fragment.WeatherInfoFragment;
import com.jiuselu.helloworld.ui.UIUtils;
import com.jiuselu.helloworld.utils.HttpUtil;
import com.jiuselu.helloworld.utils.PrefUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WeatherActivity extends AppCompatActivity {

    private static final String TAG = "WeatherActivity";
    private ImageView ivBg;
    private DrawerLayout drawerLayout;
    private ViewPager viewPager;
    private ChooseAreaFragment chooseFragment;
    private LinearLayout viewGroup;
    private ImageView navPage1;
    private ImageView navPage2;
    private ImageView navPage3;
    private ArrayList<WeatherInfoFragment> weatherInfoFragments;
    private ImageView[] imageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initView();
    }

    private void initView() {
        ivBg = (ImageView) findViewById(R.id.iv_bg);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        chooseFragment = (ChooseAreaFragment) getSupportFragmentManager().findFragmentById(R.id.choose_fragment);
        viewGroup = (LinearLayout) findViewById(R.id.view_group);
        navPage1 = (ImageView) findViewById(R.id.nav_page_1);
        navPage2 = (ImageView) findViewById(R.id.nav_page_2);
        navPage3 = (ImageView) findViewById(R.id.nav_page_3);

        //初始化fragment并缓存fragment
        weatherInfoFragments = new ArrayList<>();
        WeatherInfoFragment weatherInfoFragment0 = new WeatherInfoFragment();
        weatherInfoFragment0.setId(0);
        weatherInfoFragments.add(weatherInfoFragment0);
        WeatherInfoFragment weatherInfoFragment1 = new WeatherInfoFragment();
        weatherInfoFragment1.setId(1);
        weatherInfoFragments.add(weatherInfoFragment1);
        WeatherInfoFragment weatherInfoFragment2 = new WeatherInfoFragment();
        weatherInfoFragment2.setId(2);
        weatherInfoFragments.add(weatherInfoFragment2);

        imageViews = new ImageView[3];
        imageViews[0] = navPage1;
        imageViews[1] = navPage2;
        imageViews[2] = navPage3;

        //设置适配器
        WeatherInfoAdapter weatherInfoAdapter = new WeatherInfoAdapter(getSupportFragmentManager(), weatherInfoFragments);
        viewPager.setAdapter(weatherInfoAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Log.d(TAG, "onPageSelected:pager的位置 " + position);
                for (int i = 0;i < imageViews.length;i++){
                    if (i == position){
                        imageViews[i].setImageResource(R.drawable.page_indicator_focused);
                    }else{
                        imageViews[i].setImageResource(R.drawable.page_indicator_unfocused);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        String pic = PrefUtils.getString(UIUtils.getContext(), "pic", "");
        if (!TextUtils.isEmpty(pic)){
            Glide.with(WeatherActivity.this).load(pic).into(ivBg);
        }else {
            loadBigPic();
        }
    }

    /**
     * 加载大图片
     */
    private void loadBigPic() {
        String url = "http://guolin.tech/api/bing_pic";
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responsePic = response.body().string();
                PrefUtils.setString(UIUtils.getContext(),"pic",responsePic);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Glide.with(WeatherActivity.this).load(responsePic).into(ivBg);
                    }
                });
            }
        });
    }
}
