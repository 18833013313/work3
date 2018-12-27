package bw.com.yk.Util;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class UtilWork  {
    private static UtilWork instance;

    private UtilWork() {
    }

    public static UtilWork getInstance() {
        if (instance ==null) {
            synchronized (UtilWork.class) {
                if (instance == null) {
                    instance = new UtilWork();
                }
            }
        }
        return instance;
    }
    public interface CallBack<T>{
        void onssuess(T o);
        void onFil(String msg);
    }
  /*  public void getRequest3(final String lujin, final Class classBean, final CallBack callBack){
        new AsyncTask<String, Void, Object>() {
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
                        Object o = new Gson().fromJson(json, classBean);
                        return o;
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }.execute(lujin);
    }*/
   public void getRequest3(final String lujin, final Class classBean, final CallBack callBack){
        new AsyncTask<String, Void, Object>() {
            @Override
            protected Object doInBackground(String... strings) {

                return getRequest2(strings[0],classBean);
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                if (o == null){
                    callBack.onFil("请求失败");
                }else {
                    callBack.onssuess(o);
                }
            }
        }.execute(lujin);
    }
    public <T> T getRequest2(String lujin,Class classBean){
        Object o;
        String reuest = getReuest(lujin);
         o = new Gson().fromJson(reuest, classBean);
        return (T) o;
    }
    public String getReuest(String lujin){
        String json = "";
        try {
            URL url = new URL(lujin);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            int responseCode = urlConnection.getResponseCode();
            if (responseCode == 200){
                json = stream2String(urlConnection.getInputStream());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json;
    }

    private String stream2String(InputStream inputStream) throws Exception {
        StringBuilder stringBuilder = new StringBuilder();
        InputStreamReader isr = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(isr);
        for (String tmp = br.readLine();tmp != null;tmp = br.readLine()){
            stringBuilder.append(tmp);
        }
        return stringBuilder.toString();
    }
}
