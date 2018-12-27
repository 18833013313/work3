package bw.com.lx_mvp.model.user;

import android.os.Handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import bw.com.lx_mvp.api.UserApi;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class RegModel {
    Handler handler = new Handler();
    private RegCallback mRegCallback;

    public void reg(HashMap<String,String> parmas){
        //okhttp网络请求框架
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
        //对请求体,构建数据的过程,建造者模式
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String, String> p : parmas.entrySet()) {

            formBody.add(p.getKey(),p.getValue());

        }
        //创建请求信息对象
        Request request = new Request.Builder()
                .url(UserApi.USER_REG)
                .post(formBody.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (mRegCallback!=null){
                    //产品经理定文案

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            mRegCallback.onFailure("网络异常，请稍后再试");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });
    }
    public void login(HashMap<String,String> parmas){

    }
    public void setmRegCallback(RegCallback mRegCallback){
        this.mRegCallback = mRegCallback;
    }
    public interface RegCallback{
        void onFailure(String msg);
        void onResponse(String result);
    }
}
