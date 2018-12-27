package bw.com.rk_day02.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import bw.com.rk_day02.R;
import bw.com.rk_day02.persont.ApiPerson;

public class ApiAdapter extends BaseAdapter {
    private Context context;
    private List<ApiPerson.DataBean> list;
    int a = 0;
    public ApiAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<ApiPerson.DataBean> list) {
        if (list != null){
            this.list = list;
        }
        notifyDataSetChanged();
    }
    public void addList(List<ApiPerson.DataBean> list) {
        if (list != null){
            this.list.addAll(list);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (position%2==0){
            a=1;
            return 0;
        }
        a=2;
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            if (a==1){
                convertView = LayoutInflater.from(context).inflate(R.layout.item1,parent,false);

            }else {
                convertView = LayoutInflater.from(context).inflate(R.layout.item2,parent,false);

            }
            holder = new ViewHolder(convertView);
        }else {
           holder =(ViewHolder) convertView.getTag();
        }
        holder.price.setText(list.get(position).getDetailUrl());
        return convertView;
    }
    class ViewHolder{
        TextView price;
        ImageView tupian;

        public ViewHolder(View convertView) {
            price = convertView.findViewById(R.id.price);
            tupian = convertView.findViewById(R.id.tupian);
            convertView.setTag(this);
        }
    }
}
