package bw.com.lx_mvp.presenter;

import java.util.HashMap;

import bw.com.lx_mvp.actity.Main2Activity;
import bw.com.lx_mvp.entity.UserEntity;
import bw.com.lx_mvp.model.LoginModel;
import bw.com.lx_mvp.net.RequestCallback;
import bw.com.lx_mvp.utils.ValidatorUtil;
import bw.com.lx_mvp.view.IloginView;

public class LoginPresenter {
    private LoginModel loginModel;
    private IloginView iloginView;

    public LoginPresenter(IloginView iloginView) {
        loginModel = new LoginModel();
        this.iloginView = iloginView;
    }
    public void login(HashMap<String,String> params){
        String mobile ="18612991023";
        String password = "222222";
        //验证手机号码
        if (!ValidatorUtil.isMobile(mobile)){
            if (iloginView!=null){
                iloginView.mobileError("请输入合法手机号");

            }
            return;
        }
        //调用loginmodel的数据处理方法,登录的方法
        if (loginModel!=null){
            loginModel.login(params, new RequestCallback() {
                @Override
                public void failure(String msg) {
                    if (iloginView!=null)
                        iloginView.failure(msg);
                }

                @Override
                public void successMsg(String msg) {
                    if (iloginView!=null)
                        iloginView.successMsg(msg);
                }

                @Override
                public void success(UserEntity userEntity) {
                    if (iloginView!=null)
                        iloginView.success(userEntity);
                }
            });
        }
    }
}
