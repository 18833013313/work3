package bw.com.lx_mvp.contract;

import java.util.HashMap;

public interface IRegContract {
    /**
     * p层的抽象方法
     */
    public abstract class RegPresent{
        public abstract void register(HashMap<String,String> params);
        public abstract void login(HashMap<String,String> params);
    }
    /**
     * m层回调接口
     */
    interface  IRegModle{
        void reg(HashMap<String,String> parmas);
        void login(HashMap<String,String> parmas);
    }

    /**
     * v层回调接口
     */
    interface IRegView{
        void mobileError(String error);
        void success(String result);
        void failure(String msg);
    }
}
