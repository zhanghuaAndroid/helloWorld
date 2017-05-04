package com.jiuselu.helloworld.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.jiuselu.helloworld.fragment.WeatherInfoFragment;

import java.util.ArrayList;

/**
 * Description: ：
 * Company      :
 * Author       :
 * E-Mail       :
 * Date         ：2017/5/4
 * Tell         :
 */
public class WeatherInfoAdapter extends FragmentPagerAdapter {

    private static final String TAG = "WeatherInfoAdapter";

    private ArrayList<WeatherInfoFragment> weatherInfoFragments;
    private WeatherInfoFragment currentFragment;

    public WeatherInfoAdapter(FragmentManager fm, ArrayList<WeatherInfoFragment> weatherInfoFragments) {
        super(fm);
        this.weatherInfoFragments = weatherInfoFragments;
        if (weatherInfoFragments.size() > 0){
            currentFragment = weatherInfoFragments.get(0);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return weatherInfoFragments.get(position);
    }

    @Override
    public int getCount() {
        return weatherInfoFragments == null ? 0 : weatherInfoFragments.size();
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        currentFragment = (WeatherInfoFragment) object;
    }
}
