package bw.com.yk.Fragmenty;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import bw.com.yk.Adapter.NewsAdapter;
import bw.com.yk.Person.NewsPerson;
import bw.com.yk.R;
import bw.com.yk.Util.UtilWork;

public class Fragment3 extends Fragment {

    private PullToRefreshListView plv;
    private NewsAdapter adapter;
   // private String newsUrl= "http://172.17.8.100/movieApi/movie/v1/findReleaseMovieList?";
    private int page=1;
    private int count = 10;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment3, container, false);
        plv = view.findViewById(R.id.plv1);
        adapter = new NewsAdapter(getActivity());
        plv.setAdapter(adapter);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page=1;
                initData();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initData();
            }
        });
        page = 1;
             initData();


        return view;
    }

private String newurl="http://172.17.8.100/movieApi/movie/v1/findReleaseMovieList?";
    private void initData() {
        UtilWork.getInstance().getRequest3(newurl+"page=" + page + "&count="+count,
                NewsPerson.class, new UtilWork.CallBack<NewsPerson>() {
                    @Override
                    public void onssuess(NewsPerson o) {
                        if (page == 1){
                            adapter.setList(o.getResult());
                        } else {
                            adapter.addList(o.getResult());
                        }
                        page++;
                        plv.onRefreshComplete();

                    }

                    @Override
                    public void onFil(String msg) {
                        Toast.makeText(getActivity(),"请求错误",Toast.LENGTH_SHORT).show();
                        plv.onRefreshComplete();
                    }
                });
    }


}
