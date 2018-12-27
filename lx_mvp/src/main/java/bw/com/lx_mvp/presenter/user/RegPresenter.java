package bw.com.lx_mvp.presenter.user;

import java.util.HashMap;

import bw.com.lx_mvp.contract.IRegContract;
import bw.com.lx_mvp.model.user.RegModel;
import bw.com.lx_mvp.utils.ValidatorUtil;

public class RegPresenter{
    private RegModel mRegModel;
    private IRegContract.IRegView iRegView;
    /**
     * 绑定
     */
    public RegPresenter(IRegContract.IRegView iRegView){
        this.mRegModel = new RegModel();
        this.iRegView = iRegView;
    }
    public void register(HashMap<String,String> params){
        String mobile = params.get("mogile");
        if (!ValidatorUtil.isMobile(mobile)) {
            if (iRegView != null) {
                iRegView.mobileError("手机号不合法");
            }
            return;
        }
        if (mRegModel != null){
            mRegModel.reg(params);
            mRegModel.setmRegCallback(new RegModel.RegCallback() {
                @Override
                public void onFailure(String msg) {
                    if (iRegView != null) {
                        iRegView.failure(msg);
                    }
                }

                @Override
                public void onResponse(String result) {
                    if (iRegView != null) {
                        iRegView.success(result);
                    }
                }
            });
        }
    }
    public void login(HashMap<String,String> params){

    }
    /**
     * 解决内存泄漏
     */
    public void destroy(){
        if (iRegView!=null){
            iRegView = null;
        }
    }
}
