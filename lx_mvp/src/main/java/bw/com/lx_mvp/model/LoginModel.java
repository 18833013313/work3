package bw.com.lx_mvp.model;

import android.os.Handler;
import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import bw.com.lx_mvp.api.UserApi;
import bw.com.lx_mvp.entity.UserEntity;
import bw.com.lx_mvp.net.RequestCallback;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginModel {
    Handler handler = new Handler();
    public void login(HashMap<String,String> params, final RequestCallback callback){
        //okhttp网络框架的管理类
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
        //对请求体,构建数据的过程.建造者模式
        FormBody.Builder formBody = new FormBody.Builder();
        for (Map.Entry<String,String> p : params.entrySet()){
            formBody.add(p.getKey(),p.getValue());
        }

        //创建请求信息对象
        Request request = new Request.Builder()
                //地址
                .url(UserApi.USER_LOGIN)
                .build();
        //创建请求执行对象
        Call call = okHttpClient.newCall(request);
        //异步请求
        call.enqueue(new Callback() {
            //失败
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callback.failure("网络可能不稳定,请稍后再试");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                int code = response.code();
                if (!TextUtils.isEmpty(result)){
                    paserResult(result,callback,code);
                }
            }
        });
    }

    private void paserResult(String result, final RequestCallback callback, int code) {
        final UserEntity userEntity = new Gson().fromJson(result, UserEntity.class);

        if (callback != null){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    callback.success(userEntity);
                }
            });
        }
    }
}
