package com.jiuselu.helloworld.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiuselu.helloworld.R;

/**
 * Description: ：
 * Company      :
 * Author       :
 * E-Mail       :
 * Date         ：2017/4/28
 * Tell         :
 */
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder>{

    private Context cxt;
    public WeatherAdapter(Context cxt){
        this.cxt = cxt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(cxt, R.layout.layout_weather_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvAddress;
        public ViewHolder(View itemView) {
            super(itemView);
            tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
        }
    }

}
