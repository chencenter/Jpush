package com.like.jpush.sample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.like.jpush.JpushReceiver;
import com.like.jpush.JpushUtils;
import com.like.logger.Logger;
import com.like.rxbus.RxBus;
import com.like.rxbus.annotations.RxBusSubscribe;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        JpushUtils.getInstance(this).debugAndInit();
        JpushUtils.getInstance(this).setAlias("like");
        JpushUtils.getInstance(this).setTags("like1,like2");
        RxBus.register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RxBus.unregister(this);
    }

    @RxBusSubscribe(JpushReceiver.TAG_CLICK_NOTIFICATION)
    public void onNotificationClick(Intent intent) {
        // intent中的内容是由后台决定的。
        Logger.i("点击了极光推送的通知：" + intent.getData());
    }
}
