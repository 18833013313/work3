package bw.com.lx_yk_1.Fragment;

import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import bw.com.lx_yk_1.Adapter.NewsAdapter;
import bw.com.lx_yk_1.Person.NewsPerson;
import bw.com.lx_yk_1.R;
import bw.com.lx_yk_1.UserDao.UserDao;
import bw.com.lx_yk_1.Util.NewsWork;

public class Fragment4 extends BaseFragment{

    private PullToRefreshListView plv;
    private String apiUrl = "http://api.expoon.com/AppNews/getNewsList/type/1/p/";
    private int page;
    private NewsAdapter adapter;
    private UserDao dao;

    @Override
    protected void initData() {

    }

    @Override
    protected void initView(View view) {
        plv = view.findViewById(R.id.plv);
        adapter = new NewsAdapter(getActivity());
        plv.setAdapter(adapter);
        plv.onRefreshComplete();
        dao = new UserDao(getActivity());
        plv.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page = 1;
                initRequet();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                initRequet();
            }
        });
        page = 1;
        initRequet();
    }

    private void initRequet() {
        NewsWork.getInstance().getrequeslt(apiUrl + page, NewsPerson.class,
                new NewsWork.NewCallback<NewsPerson>() {
                    @Override
                    public void onsccess(NewsPerson o) {
                        if (page == 1){
                            adapter.setList(o.getData());

                            for (int i = 0;i<o.getData().size();i++){
                                String news_summary = o.getData().get(i).getNews_summary();
                                String title = o.getData().get(i).getNews_title();
                                String url = o.getData().get(i).getPic_url();
                                dao.insert(title,news_summary,url);
                            }

                        }else {
                            adapter.addList(o.getData());
                           // dao.insert((NewsPerson.DataBean) o.getData());
                           for (int i = 0;i<o.getData().size();i++){
                                String news_summary = o.getData().get(i).getNews_summary();
                                String title = o.getData().get(i).getNews_title();
                                String url = o.getData().get(i).getPic_url();
                               dao.insert(title,news_summary,url);
                            }
                        }
                        page++;
                        plv.onRefreshComplete();
                        if (o.getData().size()<0){
                            plv.setMode(PullToRefreshBase.Mode.PULL_FROM_START);
                        }else {
                            plv.setMode(PullToRefreshBase.Mode.BOTH);
                        }
                    }

                    @Override
                    public void onFil(String msg) {
                        plv.onRefreshComplete();
                        Toast.makeText(getActivity(), "请求Banner错误", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    @Override
    protected int getRequtId() {
        return R.layout.fragment4;
    }
}
