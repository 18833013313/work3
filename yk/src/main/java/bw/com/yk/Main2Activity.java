package bw.com.yk;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import bw.com.yk.Activityt.BaseActivity;
import bw.com.yk.Fragmenty.Fragment1;
import bw.com.yk.Fragmenty.Fragmentkong;

public class Main2Activity extends BaseActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_a:
                    pager.setCurrentItem(0);
                    return true;
                case R.id.navigation_home:
                    pager.setCurrentItem(1);
                    return true;
                case R.id.navigation_dashboard:
                    pager.setCurrentItem(2);
                    return true;
                case R.id.navigation_notifications:
                    pager.setCurrentItem(3);
                    return true;
                case R.id.navigation_b:
                    pager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };
    private ViewPager pager;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }*/

    @Override
    protected void initData() {
        //设置适配器
        pager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                switch (i){
                    case 0:
                        return new Fragment1();
                    default:
                        return new Fragmentkong();
                }

            }

            @Override
            public int getCount() {
                return 5;
            }
        });
    }

    @Override
    protected void initView() {
        mTextMessage = (TextView) findViewById(R.id.message);
        final BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        pager = findViewById(R.id.pager);
        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i){
                    case 0:
                        navigation.setSelectedItemId(R.id.navigation_a);
                    case 1:
                        navigation.setSelectedItemId(R.id.navigation_home);
                    case 2:
                        navigation.setSelectedItemId(R.id.navigation_dashboard);
                    case 3:
                        navigation.setSelectedItemId(R.id.navigation_notifications);
                    case 4:
                        navigation.setSelectedItemId(R.id.navigation_b);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void request() {
        setContentView(R.layout.activity_main2);
    }

}
