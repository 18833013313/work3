package bw.com.yk.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import bw.com.yk.Fragmenty.Fragment2;
import bw.com.yk.Fragmenty.Fragment3;

public class TitleAdapter extends FragmentPagerAdapter {
    private String[] s = new String[]{
            "正在上映","即将上映"
    };
    public TitleAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new Fragment2();
            case 1:
                return new Fragment3();
        }
        return null;
    }

    @Override
    public int getCount() {
        return s.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return s[position];
    }
}
