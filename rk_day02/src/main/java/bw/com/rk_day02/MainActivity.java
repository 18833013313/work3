package bw.com.rk_day02;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import bw.com.rk_day02.adapter.ApiAdapter;
import bw.com.rk_day02.persont.ApiPerson;

public class MainActivity extends AppCompatActivity {

    private PullToRefreshListView plv;
    private String apiUrl = "http://120.27.23.105/product/getProducts?pscid=39&page=";
    private int page = 1;
    private ApiAdapter apiAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        plv = findViewById(R.id.plv);
        apiAdapter = new ApiAdapter(this);
        plv.setAdapter(apiAdapter);
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
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
        new AsyncTask<String, Void, ApiPerson>() {

            private ApiPerson apiPerson;

            @Override
            protected ApiPerson doInBackground(String... strings) {

                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");
                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);
                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200){
                        String json = stream2String(urlConnection.getInputStream());
                        apiPerson = new Gson().fromJson(json, ApiPerson.class);
                        return apiPerson;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(ApiPerson apiPerson) {
                super.onPostExecute(apiPerson);
                if (apiPerson !=null){
                    apiAdapter.setList(apiPerson.getData());
                }else {
                    apiAdapter.addList(apiPerson.getData());
                }
            }
        }.execute(apiUrl+page);
    }
        //tianjia
    private String stream2String(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        for (String tmp = br.readLine();tmp!= null;tmp= br.readLine()){
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }
}
