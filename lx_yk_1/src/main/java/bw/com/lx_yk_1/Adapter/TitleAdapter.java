package bw.com.lx_yk_1.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.andy.library.ChannelBean;

import java.util.ArrayList;
import java.util.List;

import bw.com.lx_yk_1.Fragment.Fragment3;
import bw.com.lx_yk_1.Fragment.Fragment4;

public class TitleAdapter extends FragmentPagerAdapter {
   /* private String[] s = new String[]{
            "要闻","汽车","军事",
    };*/
    private List<ChannelBean> list;

    public TitleAdapter(FragmentManager fm) {
        super(fm);
         list = new ArrayList<>();
    }

    public void setList(List<ChannelBean> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new Fragment4();
                default:
                    return new Fragment3();
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (list.get(position).isSelect()){
            return list.get(position).getName();
        }
        return null;
    }
}
