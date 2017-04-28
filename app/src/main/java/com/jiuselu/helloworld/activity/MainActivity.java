package com.jiuselu.helloworld.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiuselu.helloworld.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //一个全新的开始
        setContentView(R.layout.activity_main);
    }
}
