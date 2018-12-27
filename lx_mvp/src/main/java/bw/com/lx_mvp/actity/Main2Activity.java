package bw.com.lx_mvp.actity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import bw.com.lx_mvp.R;
import bw.com.lx_mvp.entity.UserEntity;
import bw.com.lx_mvp.presenter.LoginPresenter;
import bw.com.lx_mvp.view.IloginView;

public class Main2Activity extends AppCompatActivity implements IloginView {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initData();
    }

    private void initData() {
        presenter = new LoginPresenter(this);

    }
    public void login(View view){
        HashMap<String,String> params = new HashMap<>();
        params.put("mobile","18612991023");
        params.put("password","222222");
        if (presenter!=null){
            presenter.login(params);
        }
    }

    @Override
    public void mobileError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void pwdError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(UserEntity userEntity) {
        Toast.makeText(this,userEntity.msg+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void successMsg(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
