package inc.shijj.supernews;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;
import java.util.List;
import inc.shijj.supernews.adapter.MyAdapter;
import inc.shijj.supernews.api.HttpApi;
import inc.shijj.supernews.beans.NewsBean;
import inc.shijj.supernews.utils.HttpUtil;
import inc.shijj.supernews.utils.JsonUtil;


public class MainActivity extends AppCompatActivity {
    private HttpUtil httpUtil = null;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLayoutManager;
    private MyAdapter mAdapter;
    public NewsBean newsBean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        initDatas();
    }


    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recyler);
        //创建默认的线性LayoutManager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        mRecyclerView.setHasFixedSize(true);

    }

    private void initDatas() {
        httpUtil = new HttpUtil();
        httpUtil.getJson(HttpApi.mainUrl, new HttpUtil.HttpCallBack() {
            @Override
            public void onSusscess(final String data) {
                newsBean = JsonUtil.parseJsonToBean(data, NewsBean.class);
                if (newsBean != null) {
                    final List<NewsBean.DataBean> dataBean = newsBean.getData();
                    //创建并设置Adapter
                    mAdapter = new MyAdapter(dataBean);
                    mRecyclerView.setAdapter(mAdapter);
                    //开始点击回调
                    mAdapter.setOnItemClickListener(new MyAdapter.OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Toast.makeText(App.application, dataBean.get(position).getName() + "", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}
