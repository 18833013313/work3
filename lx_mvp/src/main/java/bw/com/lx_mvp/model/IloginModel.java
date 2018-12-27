package bw.com.lx_mvp.model;

import java.util.HashMap;

import bw.com.lx_mvp.net.RequestCallback;

public interface IloginModel {
    void login(HashMap<String,String> params, RequestCallback requestCallback);
}
