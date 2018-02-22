package com.example.demo02_netreceiver;

/**
 * 创建日期：2018/2/22 on 下午8:00
 * 描述:
 * 作者:yangliang
 */
public class Person {

    private String name;
    private String content;
    private String time;

    public Person() {
    }

    public Person(String name, String content, String time) {
        this.name = name;
        this.content = content;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
