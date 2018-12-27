package bw.com.lx_mvp.actity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.HashMap;

import bw.com.lx_mvp.R;

public class RegActivity extends AppCompatActivity {

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
}
