package com.example.demo01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * 创建日期：2018/2/19 on 下午8:50
 * 描述: 注册和取消订阅事件
 * 作者: liangyang
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化视图
        textView = (TextView) findViewById(R.id.tv_content);
        Button btnRegister = (Button) findViewById(R.id.btn_register);
        Button btnUnRegister = (Button) findViewById(R.id.btn_unregister);
        Button buttonJump = (Button) findViewById(R.id.btn_jump);
        Button buttonJumpSticky = (Button) findViewById(R.id.btn_jump_sticky);

        //注册订阅事件
        btnRegister.setOnClickListener(this);
        btnUnRegister.setOnClickListener(this);

        //点击事件跳转界面
        buttonJump.setOnClickListener(this);
        buttonJumpSticky.setOnClickListener(this);


//        //注册事件
//        EventBus.getDefault().register(this);

    }

    /**
     * 事件订阅者处理事件（名字可以任意，但是要添加@Subscribe）
     * ThreadMode设置为MAIN，事件的处理会在UI线程中执行，用TextView来展示收到的事件消息
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMoonEvent(MessageEvent messageEvent) {
        textView.setText(messageEvent.getMseeage());
    }

    /**
     * 新写一个方法用来处理粘性事件
     *
     * @param messageEvent
     */
    @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void onMoonStickyEvent(MessageEvent messageEvent) {
        textView.setText(messageEvent.getMseeage());
    }

    /**
     * 注意，一定要取消注册事件
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_register:
                //在注册的时候需要加一个注册保护，去判断是否该组件已经注册了
                if (!EventBus.getDefault().isRegistered(MainActivity.this)) {
                    EventBus.getDefault().register(MainActivity.this);
                    Toast.makeText(this, "注册订阅事件成功~", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "已注册订阅事件~", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btn_unregister:
                if (EventBus.getDefault().isRegistered(MainActivity.this)) {
                    EventBus.getDefault().unregister(this);
                    Toast.makeText(this, "手动取消注册订阅事件成功~", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "还未注册订阅事件~", Toast.LENGTH_SHORT).show();
                }
                break;


            case R.id.btn_jump:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;

            case R.id.btn_jump_sticky:
                startActivity(new Intent(MainActivity.this, ThirdActivity.class));
                break;

            default:
                break;
        }

    }
}
