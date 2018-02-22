package com.example.demo01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

/**
 * 创建日期：2018/2/22 on 下午6:41
 * 描述: 事件发布者发布事件
 * 作者: liangyang
 */
public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Button button = (Button) findViewById(R.id.btn_send);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用post发送事件
                EventBus.getDefault().post(new MessageEvent("EventBus事件"));
                finish();
            }
        });
    }
}
