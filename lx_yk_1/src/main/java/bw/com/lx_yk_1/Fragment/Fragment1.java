package bw.com.lx_yk_1.Fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.andy.library.ChannelActivity;
import com.andy.library.ChannelBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import bw.com.lx_yk_1.Adapter.TitleAdapter;
import bw.com.lx_yk_1.R;

import static com.andy.library.ChannelActivity.REQUEST_CODE;
import static com.andy.library.ChannelActivity.RESULT_JSON_KEY;

public class Fragment1 extends BaseFragment{

    private TabLayout tab;
    private ViewPager page;
    private Button b1;
    private ArrayList<ChannelBean> list;
    private TitleAdapter adapter;

    @Override
    protected void initData() {
        list = new ArrayList<>();
        for (int i = 1; i<5;i++) {
            list.add(new ChannelBean(""+i, true));
        }
        for (int i = 5; i<9;i++) {
            list.add(new ChannelBean(""+i, false));
        }
        adapter.setList(list);
    }

    @Override
    protected void initView(View view) {
        tab = view.findViewById(R.id.tab);
        page = view.findViewById(R.id.page1);
        adapter = new TitleAdapter(getChildFragmentManager());
        page.setAdapter(adapter);
        tab.setupWithViewPager(page);
        b1 = view.findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //ChannelActivity.startChannelActivity((AppCompatActivity) getActivity(),list);
                //getActivity().startActivityForResult();
                Gson gson = new Gson();
                String jsonArray = gson.toJson(list);
                Intent intent = new Intent(getActivity(), ChannelActivity.class);
                intent.putExtra(RESULT_JSON_KEY, jsonArray);

                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE&&resultCode == ChannelActivity.RESULT_CODE ){
            String stringExtra = data.getStringExtra(RESULT_JSON_KEY);
            Gson gson = new Gson();
            //List<ChannelBean> o = (List<ChannelBean>) gson.fromJson(stringExtra,new TypeToken<List<ChannelBean>>(){}.getType());
            List<ChannelBean> o = gson.fromJson(stringExtra, new TypeToken<List<ChannelBean>>() {
            }.getType());
            adapter.setList(o);
        }
    }

    @Override
    protected int getRequtId() {
        return R.layout.fragment1;
    }
}
