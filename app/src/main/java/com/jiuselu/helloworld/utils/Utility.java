package com.jiuselu.helloworld.utils;

import android.text.TextUtils;

import com.jiuselu.helloworld.db.City;
import com.jiuselu.helloworld.db.County;
import com.jiuselu.helloworld.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Description: ：
 * Company      : 北京云磅科技
 * Author       :
 * E-Mail       :
 * Date         ：2017/4/28
 * Tell         :
 */
public class Utility {

    public static boolean handleProvinceResponse(String response) throws JSONException {
        if (!TextUtils.isEmpty(response)) {
            JSONArray allProvinces = new JSONArray();
            for (int i = 0; i < allProvinces.length(); i++) {
                JSONObject jsonObject = allProvinces.getJSONObject(i);
                Province province = new Province();
                province.setPrvinceName(jsonObject.getString("name"));
                province.setPrvinceCode(jsonObject.getInt("id"));
                province.save();
            }
            return true;
        }
        return false;
    }

    public static boolean handleCityResponse(String response) throws JSONException {
        if (!TextUtils.isEmpty(response)) {
            JSONArray allCitys = new JSONArray();
            for (int i = 0; i < allCitys.length(); i++) {
                JSONObject jsonObject = allCitys.getJSONObject(i);
                City city = new City();
                city.setCityName(jsonObject.getString("name"));
                city.setCityCode(jsonObject.getInt("id"));
                city.save();
            }
            return true;
        }
        return false;
    }

    public static boolean handleCountyResponse(String response) throws JSONException {
        if (!TextUtils.isEmpty(response)) {
            JSONArray allCounty = new JSONArray();
            for (int i = 0; i < allCounty.length(); i++) {
                JSONObject jsonObject = allCounty.getJSONObject(i);
                County county = new County();
                county.setCountyName(jsonObject.getString("name"));
                county.setCountyCode(jsonObject.getInt("id"));
                county.save();
            }
            return true;
        }
        return false;
    }

}
