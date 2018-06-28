package com.xuechuan.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AbsListView;

import com.andview.refreshview.XRefreshView;
import com.andview.refreshview.listener.OnBottomLoadMoreTime;
import com.andview.refreshview.listener.OnTopRefreshTime;
import com.xuechuan.myapplication.adapter.StickyListAdapter;
import com.xuechuan.myapplication.adapter.StickyListBean;
import com.xuechuan.myapplication.smileyloadingview.SmileyHeaderView;
import com.xuechuan.myapplication.stickyListHeaders.StickyListHeadersAdapter;
import com.xuechuan.myapplication.stickyListHeaders.StickyListHeadersListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<StickyListBean> list = new ArrayList<StickyListBean>();
    private StickyListHeadersListView mStickyList;
    private XRefreshView mCustomView;
    private int mTotalItemCount;
    private ArrayList<SelectData> selectData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
    }

    private void initAdapter() {
        StickyListAdapter adapter = new StickyListAdapter(getApplicationContext(), list,selectData);
        mStickyList.setAdapter(adapter);
        mCustomView.setPullLoadEnable(true);
        mCustomView.setCustomHeaderView(new SmileyHeaderView(this));
        mCustomView.setCustomFooterView(new CustomFooterView(this));
        mCustomView.setOnTopRefreshTime(new OnTopRefreshTime() {

            @Override
            public boolean isTop() {
                if (mStickyList.getFirstVisiblePosition() == 0) {
                    View firstVisibleChild = mStickyList.getListChildAt(0);
                    return firstVisibleChild.getTop() >= 0;
                }
                return false;
            }
        });
        mCustomView.setOnBottomLoadMoreTime(new OnBottomLoadMoreTime() {

            @Override
            public boolean isBottom() {
                if (mStickyList.getLastVisiblePosition() == mTotalItemCount - 1) {
                    View lastChild = mStickyList.getListChildAt(mStickyList.getListChildCount() - 1);
                    return (lastChild.getBottom() + mStickyList.getPaddingBottom()) <= mStickyList.getMeasuredHeight();
                }
                return false;
            }
        });
        mStickyList.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                mTotalItemCount = totalItemCount;
            }
        });
        mCustomView.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCustomView.stopRefresh();
                    }
                }, 4000);
            }

            @Override
            public void onLoadMore(boolean isSilence) {

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        mCustomView.stopLoadMore();
                    }
                }, 2000);
            }
        });
    }

    int section = 0;
    String YM = null;
    String content = null;

    private void initData() {
        selectData = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
            SelectData data = new SelectData();
            ArrayList<String> list = new ArrayList<>();
            if (i % 2 == 0) {
                section++;
                YM = "第" + section + "个头";
                list.add(YM);
            }
            content = "第" + i + "项数据";
            data.setShow(false);
            data.setmItemList(list);
            data.setPrarenid(YM);
            selectData.add(data);
            StickyListBean bean = new StickyListBean(section, YM, content);
            this.list.add(bean);
        }

    }


    private void initView() {
        mStickyList = (StickyListHeadersListView) findViewById(R.id.sticky_list);
        mCustomView = (XRefreshView) findViewById(R.id.custom_view);
    }
}
