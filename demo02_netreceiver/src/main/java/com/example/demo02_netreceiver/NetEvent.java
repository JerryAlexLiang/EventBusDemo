package com.example.demo02_netreceiver;

/**
 * 创建日期：2018/1/12 on 下午1:45
 * 描述:用于网络的事件组件间通信
 * 作者:yangliang
 */
public class NetEvent {

    public boolean isNet;

    public NetEvent(boolean isNet) {

        this.isNet = isNet;
    }

    public boolean isNet() {
        return isNet;
    }


}
