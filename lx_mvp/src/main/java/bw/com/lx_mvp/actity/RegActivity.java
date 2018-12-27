package bw.com.lx_mvp.actity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import bw.com.lx_mvp.R;
import bw.com.lx_mvp.view.user.IRegVIew;

public class RegActivity extends AppCompatActivity implements IRegVIew {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);
        initData();
    }

    private void initData() {

    }
    /**
     * 注册
     */
    public void regBtn(View view){
        HashMap<String,String> params = new HashMap<>();
        params.put("mobile","18623456789");
        params.put("password","111111");


    }

    @Override
    public void mobileError(String error) {
        Toast.makeText(this,"mobileError",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void success(String result) {
        Toast.makeText(this,"success",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,"failure",Toast.LENGTH_SHORT).show();
    }
}
