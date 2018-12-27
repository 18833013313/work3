package bw.com.yk.Fragmenty;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bw.com.yk.Adapter.TitleAdapter;
import bw.com.yk.R;

public class Fragment1 extends Fragment {

    private ViewPager pager;
    private TabLayout tab;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        pager = view.findViewById(R.id.pager1);
        tab = view.findViewById(R.id.tab);
        pager.setAdapter(new TitleAdapter(getChildFragmentManager()));
        tab.setupWithViewPager(pager);
    }
}
