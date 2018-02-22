package com.example.demo02_netreceiver;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;

/**
 * 创建日期：2018/2/22 on 下午8:01
 * 描述: 使用广播及EventBus  实现网络状态栏动态变换
 * 作者: liangyang
 */
public class MainActivity extends AppCompatActivity {

    private NetReceiver netReceiver;
    private LinkedList<Person> dataList;
    private ListView listView;
    private PersonAdapter adapter;
    private RelativeLayout rlNetBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rlNetBar = (RelativeLayout) findViewById(R.id.rl_net_bar);

        //注册监听网络状态的广播接收者
        initReceive();

        //订阅EventBus事件
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }

//        EventBus.getDefault().register(this);

        initView();
    }

    private void initView() {
        dataList = new LinkedList<>();

        dataList.add(new Person("王俊凯", "青春修炼手册", "15:30"));
        dataList.add(new Person("王源", "青春修炼手册", "16:30"));
        dataList.add(new Person("马天宇", "该死的温柔", "12:30"));
        dataList.add(new Person("杨洋", "微微一笑很倾城", "13:50"));
        dataList.add(new Person("侯明昊", "偶像万万岁", "18:32"));
        dataList.add(new Person("李钟硕", "trouble maker", "18:45"));
        dataList.add(new Person("王俊凯", "青春修炼手册", "15:35"));
        dataList.add(new Person("王源", "青春修炼手册", "16:38"));
        dataList.add(new Person("马天宇", "该死的温柔", "12:20"));
        dataList.add(new Person("杨洋", "微微一笑很倾城", "13:25"));
        dataList.add(new Person("侯明昊", "偶像万万岁", "22:30"));
        dataList.add(new Person("李钟硕", "trouble maker", "20:30"));

        //按时间排序数组（ListView）
        Collections.sort(dataList, new Comparator<Person>() {
            /**
             * @param lhs
             * @param rhs
             * @returnan 比较数据大小时, 这里比的是时间
             */
            @Override
            public int compare(Person lhs, Person rhs) {
                Date date1 = DateParseUtils.stringToDate(lhs.getTime());
                Date date2 = DateParseUtils.stringToDate(rhs.getTime());

                // 对日期字段进行升序，如果欲降序可采用after方法
                if (date1.before(date2)) {
                    return 1;
                }
                return -1;
            }
        });

        listView = (ListView) findViewById(R.id.list_view);
        adapter = new PersonAdapter(MainActivity.this, dataList);
        listView.setAdapter(adapter);

    }

    private void initReceive() {
        netReceiver = new NetReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(netReceiver, intentFilter);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(NetEvent netEvent) {
        setNetState(netEvent.isNet());
    }

    private void setNetState(boolean netState) {
        if (rlNetBar != null) {
            rlNetBar.setVisibility(netState ? View.GONE : View.VISIBLE);
            //点击跳转手机系统设置界面
            rlNetBar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NetUtils.openSetting(MainActivity.this);
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(netReceiver);
        EventBus.getDefault().unregister(this);
    }
}
