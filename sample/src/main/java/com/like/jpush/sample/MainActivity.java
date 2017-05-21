package com.like.jpush.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.like.jpush.JpushUtils;

import cn.jpush.android.api.JPushInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        JpushUtils.getInstance(this).setAlias("like");
        JpushUtils.getInstance(this).setTag("like1,like2");
    }
}
