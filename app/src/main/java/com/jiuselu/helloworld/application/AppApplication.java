package com.jiuselu.helloworld.application;

import android.app.Application;
import android.content.Context;

import org.litepal.LitePalApplication;

/**
 * Description: ：
 * Company      :
 * Author       :
 * E-Mail       :
 * Date         ：2017/4/28
 * Tell         :
 */
public class AppApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        LitePalApplication.initialize(this);
        mContext = getApplicationContext();
        //4001c9cd6c37490e8d0fbbc899b7c8d0
    }
}
