package com.jiuselu.helloworld.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.jiuselu.helloworld.R;
import com.jiuselu.helloworld.adapter.WeatherAdapter;

/**
 * Description: ：
 * Company      : 
 * Author       :
 * E-Mail       :
 * Date         ：2017/4/28
 * Tell         :
 */
public class ChooseAreaFragment extends Fragment {

    private View view;
    private TextView tvTitle;
    private RecyclerView rvView;
    private Button btnBack;
    private Activity mActivity;

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
        rvView = (RecyclerView) view.findViewById(R.id.rv_view);
        rvView.setLayoutManager(new LinearLayoutManager(mActivity));
        WeatherAdapter weatherAdapter = new WeatherAdapter(mActivity);
        rvView.setAdapter(weatherAdapter);
    }
}
