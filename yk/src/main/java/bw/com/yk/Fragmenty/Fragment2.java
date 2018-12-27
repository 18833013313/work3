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

import bw.com.yk.Adapter.ApiAdapter;
import bw.com.yk.Person.ApiPerson;
import bw.com.yk.R;
import bw.com.yk.Util.UtilWork;

public class Fragment2 extends Fragment {

    private PullToRefreshListView plv;
    private String apiUrl = "http://172.17.8.100/movieApi/movie/v1/findHotMovieList?";
    private int page = 1;
    private int count = 10;
    private ApiAdapter apiAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plv = view.findViewById(R.id.plv);
        apiAdapter = new ApiAdapter(getActivity());
        plv.setAdapter(apiAdapter);

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
        page=1;
       initData();
    }

    private void initData() {

        UtilWork.getInstance().getRequest3(apiUrl+"page=" + page + "&count="+count,
                ApiPerson.class, new UtilWork.CallBack<ApiPerson>() {
                    @Override
                    public void onssuess(ApiPerson o) {
                        if (page == 1){
                            apiAdapter.setList(o.getResult());
                        } else {
                            apiAdapter.addList(o.getResult());
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

    /*public String straem2String(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        for (String tmp = br.readLine();tmp != null;tmp = br.readLine()){
            stringBuilder.toString();
        }
        return stringBuilder.toString();
    }*/

}
