package com.example.demo01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;

/**
 * 创建日期：2018/2/22 on 下午6:58
 * 描述: 测试粘性事件(类似粘性广播)
 * 作者: liangyang
 */
public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Button button = (Button) findViewById(R.id.btn_send_sticky);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //发送粘性事件
                EventBus.getDefault().postSticky(new MessageEvent("粘性事件"));
                finish();
            }
        });
    }
}
