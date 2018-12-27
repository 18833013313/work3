package bw.com.lx_yk_1.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import bw.com.lx_yk_1.Person.NewsPerson;
import bw.com.lx_yk_1.R;

public class NewsAdapter extends BaseAdapter {
    private Context context;
    private List<NewsPerson.DataBean> list;

    public NewsAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    public void setList(List<NewsPerson.DataBean> list) {
        if (list != null){
            this.list = list;
        }
        notifyDataSetChanged();
    }
    public void addList(List<NewsPerson.DataBean> list) {
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
    public NewsPerson.DataBean getItem(int position) {
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
        }else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.setText(getItem(position));
        return convertView;
    }
    class ViewHolder{
        TextView summary;
        TextView title;
        ImageView pic_url;

        public ViewHolder(View convertView) {
            summary = convertView.findViewById(R.id.summary);
            title = convertView.findViewById(R.id.title);
            pic_url = convertView.findViewById(R.id.pic_url);
            convertView.setTag(this);
        }

        public void setText(NewsPerson.DataBean item) {
            summary.setText(item.getNews_summary());
            title.setText(item.getNews_title());
            /*ImageLoaderConfiguration build = new ImageLoaderConfiguration.Builder(context).build();
            ImageLoader.getInstance().init(build);*/
            ImageLoader.getInstance().displayImage(item.getPic_url(),pic_url);
        }
    }
}
