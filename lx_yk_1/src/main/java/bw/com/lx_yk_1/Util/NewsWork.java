package bw.com.lx_yk_1.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import bw.com.lx_yk_1.UserDao.UserDao;

public class NewsWork {

    private static NewsWork instance;
    private Context context;
    public NewsWork() {

    }

    public static NewsWork getInstance() {
        if (instance == null){
            synchronized (NewsWork.class){
                if (instance == null){
                    instance = new NewsWork();
                }
            }
        }

        return instance;
    }
    public interface NewCallback<T>{
        void onsccess(T o);
        void onFil(String msg);
    }
    public void getrequeslt(String lujin,final Class classBean,final NewCallback callback){
        new AsyncTask<String, Void, Object>() {

            private Object o;

            @Override
            protected Object doInBackground(String... strings) {

                try {
                    URL url = new URL(strings[0]);
                    HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                    urlConnection.setRequestMethod("GET");

                    urlConnection.setConnectTimeout(5000);
                    urlConnection.setReadTimeout(5000);

                    int responseCode = urlConnection.getResponseCode();
                    if (responseCode == 200){
                        String json = stream2String(urlConnection.getInputStream());
                        o = new Gson().fromJson(json, classBean);

                        return o;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                if (o == null){
                    callback.onFil("请求失败");
                }else {
                    callback.onsccess(o);
                }
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,lujin);
    }
    private String stream2String(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        for (String tmp = br.readLine();tmp != null; tmp = br.readLine()){
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }

    public boolean isHasNotWork(Context context){
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = cm.getActiveNetworkInfo();
        return activeNetworkInfo!= null&&activeNetworkInfo.isAvailable();
    }
}
