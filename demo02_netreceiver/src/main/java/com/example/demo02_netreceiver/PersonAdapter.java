package com.example.demo02_netreceiver;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * 创建日期：2018/2/22 on 下午8:18
 * 描述:
 * 作者:yangliang
 */
public class PersonAdapter extends BaseAdapter {

    private Context mContext;
    private LinkedList<Person> dataList;
    private LayoutInflater mInflater;

    public PersonAdapter(Context mContext, LinkedList<Person> dataList) {
        this.mContext = mContext;
        this.dataList = dataList;
        this.mInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null){
            convertView = mInflater.inflate(R.layout.list_view_item,parent,false);
            viewHolder = new ViewHolder(convertView);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        //数据映射
        Person person = dataList.get(position);
        viewHolder.tvName.setText(person.getName());
        viewHolder.tvContent.setText(person.getContent());
        viewHolder.tvTime.setText(person.getTime());
        return convertView;
    }

    class ViewHolder {
        TextView tvName;
        TextView tvContent;
        TextView tvTime;

        public ViewHolder(View view) {
            view.setTag(this);
            tvName = view.findViewById(R.id.tv_name);
            tvContent = view.findViewById(R.id.tv_content);
            tvTime = view.findViewById(R.id.tv_time);
        }
    }
}
