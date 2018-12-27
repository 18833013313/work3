package bw.com.yk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import bw.com.yk.Person.ApiPerson;
import bw.com.yk.R;

public class ApiAdapter extends BaseAdapter {
    private Context context;
    private List<ApiPerson.ResultBean> list;

    public ApiAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<ApiPerson.ResultBean> list) {
        if (list!=null){
            this.list = list;
        }
        notifyDataSetChanged();
    }
    public void addList(List<ApiPerson.ResultBean> list) {
        if (list!=null){
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            holder = new ViewHolder(convertView);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.name.setText(list.get(position).getName());
        holder.summary.setText(list.get(position).getSummary());
        ImageLoader.getInstance().displayImage(list.get(position).getImageUrl(),holder.imageUrl);
        return convertView;
    }
    class ViewHolder{
        TextView name;
        TextView summary;
        ImageView imageUrl;

        public ViewHolder(View convertView) {
           this.name = convertView.findViewById(R.id.name);
            this.summary = convertView.findViewById(R.id.summary);
            imageUrl = convertView.findViewById(R.id.imageUrl);
            convertView.setTag(this);
        }


    }
}
