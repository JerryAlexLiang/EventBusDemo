package com.example.demo02_netreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

/**
 * 创建日期：2018/2/22 on 下午8:03
 * 描述: 定义用来检测网络状态的广播NetReceiver
 * 作者: liangyang
 */
public class NetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) {

            boolean isConnected = NetUtils.isConnected(context);

            if (isConnected) {
                EventBus.getDefault().post(new NetEvent(true));
                Toast.makeText(context, "已经连接网络", Toast.LENGTH_SHORT).show();
            } else {
                EventBus.getDefault().post(new NetEvent(false));
                Toast.makeText(context, "已经断开网络", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
