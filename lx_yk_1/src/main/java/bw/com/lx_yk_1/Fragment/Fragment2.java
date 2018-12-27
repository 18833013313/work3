package bw.com.lx_yk_1.Fragment;

import android.view.View;

import com.recker.flybanner.FlyBanner;

import java.util.ArrayList;

import bw.com.lx_yk_1.R;

public class Fragment2 extends BaseFragment{

    private FlyBanner fly;
    private ArrayList<Integer> integers;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        fly = view.findViewById(R.id.fly);
        integers = new ArrayList<>();
        for (int i = 0;i<10;i++){
            integers.add(R.mipmap.ic_launcher);
        }
            fly.setImages(integers);
    }


    @Override
    protected int getRequtId() {
        return R.layout.fragment2;
    }
}
