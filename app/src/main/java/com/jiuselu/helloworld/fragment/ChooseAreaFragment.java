package com.jiuselu.helloworld.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.jiuselu.helloworld.R;
import com.jiuselu.helloworld.activity.MainActivity;
import com.jiuselu.helloworld.activity.WeatherActivity;
import com.jiuselu.helloworld.adapter.WeatherAdapter;
import com.jiuselu.helloworld.db.City;
import com.jiuselu.helloworld.db.County;
import com.jiuselu.helloworld.db.Province;
import com.jiuselu.helloworld.utils.HttpUtil;
import com.jiuselu.helloworld.utils.Utility;

import org.json.JSONException;
import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Description: ：
 * Company      :
 * Author       :
 * E-Mail       :
 * Date         ：2017/4/28
 * Tell         :
 */
public class ChooseAreaFragment extends Fragment {

    private static final String TAG = "ChooseAreaFragment";

    /**
     * 常量级别
     */
    public static final int LEVEL_PROVINCE = 0;
    public static final int LEVEL_CITY = 1;
    public static final int LEVEL_COUNTY = 2;

    /**
     * 当前选中级别
     */
    public int currentLevel;

    /**
     * 用于显示的数据
     */
    private ArrayList<String> dataList = new ArrayList<String>();

    private View view;
    private TextView tvTitle;
    private ListView lvArea;
    private Button btnBack;
    private Activity mActivity;
    private WeatherAdapter weatherAdapter;
    private ProgressDialog progressDialog;
    private List<Province> provinceList;
    private Province selectProvince;
    private List<City> cities;
    private City selectCity;
    private List<County> counties;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choose_area, container, false);
        initView();
        initData();
        return view;
    }

    private void initData() {

    }

    private void initView() {
        mActivity = getActivity();
        tvTitle = (TextView) view.findViewById(R.id.tv_title);
        btnBack = (Button) view.findViewById(R.id.btn_back);
        lvArea = (ListView) view.findViewById(R.id.lv_area);
        weatherAdapter = new WeatherAdapter(dataList);
        lvArea.setAdapter(weatherAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lvArea.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (currentLevel == LEVEL_PROVINCE) {
                    //省
                    selectProvince = provinceList.get(i);
                    queryCities();
                } else if (currentLevel == LEVEL_CITY) {
                    //市
                    selectCity = cities.get(i);
                    queryCouny();
                }else if(currentLevel == LEVEL_COUNTY){
                    String weatherId = counties.get(i).getWeatherId();
                    //县
                    if (mActivity instanceof MainActivity){
                        Intent intent = new Intent(mActivity, WeatherActivity.class);
                        intent.putExtra("weatherId",weatherId);
                        startActivity(intent);
                        mActivity.finish();
                    }else if (mActivity instanceof WeatherActivity){
                        WeatherActivity wActivity = (WeatherActivity)getActivity();

                    }
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentLevel == LEVEL_COUNTY){
                    queryCities();
                }else if(currentLevel == LEVEL_CITY){
                    queryProvinces();
                }
            }
        });
        queryProvinces();
    }

    private void queryCouny() {
        tvTitle.setText(selectCity.getCityName());
        btnBack.setVisibility(View.VISIBLE);
        counties = DataSupport.where("cityid = ?", selectCity.getId() + "").find(County.class);
        if (counties.size() > 0) {
            dataList.clear();
            for (County county : counties) {
                dataList.add(county.getCountyName());
            }
            weatherAdapter.notifyDataSetChanged();
            lvArea.setSelection(0);
            currentLevel = LEVEL_COUNTY;

        } else {
            String url = "http://guolin.tech/api/china/" + selectProvince.getPrvinceCode() + "/" + selectCity.getCityCode();
            Log.d(TAG, "queryCouny: " + url);
            queryFromServer(url, LEVEL_COUNTY);
        }
    }

    private void queryCities() {
        tvTitle.setText(selectProvince.getPrvinceName());
        btnBack.setVisibility(View.VISIBLE);
        cities = DataSupport.where("provinceid = ?", selectProvince.getId() + "").find(City.class);
        if (cities.size() > 0) {
            dataList.clear();
            for (City city : cities) {
                dataList.add(city.getCityName());
            }
            weatherAdapter.notifyDataSetChanged();
            lvArea.setSelection(0);
            currentLevel = LEVEL_CITY;
        } else {
            String url = "http://guolin.tech/api/china/" + selectProvince.getPrvinceCode();
            queryFromServer(url, LEVEL_CITY);
        }
    }

    private void queryProvinces() {
        tvTitle.setText("中国");
        btnBack.setVisibility(View.GONE);
        provinceList = DataSupport.findAll(Province.class);
        if (provinceList.size() > 0) {
            dataList.clear();
            for (Province province : provinceList) {
                dataList.add(province.getPrvinceName());
            }
            weatherAdapter.notifyDataSetChanged();
            lvArea.setSelection(0);
            currentLevel = LEVEL_PROVINCE;
        } else {
            String url = "http://guolin.tech/api/china";
            queryFromServer(url, LEVEL_PROVINCE);
        }
    }

    private void queryFromServer(String url, final int level) {
        showProgressDialog();
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: 请求失败" + e);
                mActivity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        closeProgressDialog();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Log.d(TAG, "onResponse: " + s);
                //根据不同的级别解析不同的数据
                boolean result = false;
                switch (level) {
                    case LEVEL_PROVINCE:
                        result = Utility.handleProvinceResponse(s);
                        break;
                    case LEVEL_CITY:
                        result = Utility.handleCityResponse(s, selectProvince.getId());
                        break;
                    case LEVEL_COUNTY:
                        result = Utility.handleCountyResponse(s, selectCity.getId());
                        break;
                    default:
                        break;
                }
                if (result) {
                    mActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            closeProgressDialog();
                            switch (level) {
                                case LEVEL_PROVINCE:
                                    queryProvinces();
                                    break;
                                case LEVEL_CITY:
                                    queryCities();
                                    break;
                                case LEVEL_COUNTY:
                                    queryCouny();
                                    break;
                                default:
                                    break;
                            }
                        }
                    });
                }
            }
        });
    }

    /**
     * 关闭进度条
     */
    private void closeProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    /**
     * 显示进度条
     */
    private void showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(mActivity);
            progressDialog.setMessage("正在加载，请稍后...");
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.show();
    }
}
