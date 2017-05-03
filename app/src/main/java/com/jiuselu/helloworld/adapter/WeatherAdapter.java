package com.jiuselu.helloworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiuselu.helloworld.R;
import com.jiuselu.helloworld.ui.UIUtils;

import java.util.ArrayList;

/**
 * Description: ：
 * Company      :
 * Author       :
 * E-Mail       :
 * Date         ：2017/4/28
 * Tell         :
 */
public class WeatherAdapter extends BaseAdapter {

    private static final String TAG = "WeatherAdapter";

    private ArrayList<String> dataList;

    public WeatherAdapter(ArrayList<String> dataList) {
        this.dataList = dataList;
    }


    @Override
    public int getCount() {
        return dataList == null ? 0 : dataList.size();
    }

    @Override
    public String getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null) {
            view = View.inflate(UIUtils.getContext(), R.layout.layout_weather_item, null);
            holder = new ViewHolder();
            holder.tvAddress = (TextView) view.findViewById(R.id.tv_address);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tvAddress.setText(getItem(i));
        return view;
    }

    static class ViewHolder {
        public TextView tvAddress;
    }
}
