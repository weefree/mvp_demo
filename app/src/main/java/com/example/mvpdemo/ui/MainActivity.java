package com.example.mvpdemo.ui;

import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

import com.example.mvpdemo.R;
import com.example.mvpdemo.contract.MainContract;
import com.example.mvpdemo.model.entity.Picture;
import com.example.mvpdemo.presenter.MainPresenter;
import com.example.mvpdemo.ui.adapter.MainPictureAdapter;
import com.example.mvpdemo.ui.view.ItemDecoration;
import com.example.mvpdemo.utils.Logger;
import com.example.mvpdemo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements MainContract.View,MainPictureAdapter.OnClickListener{

    private MainContract.Presenter mPresenter;

    private String loadTag = "校花";

    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private MainPictureAdapter adapter;
    private List<Picture> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mPresenter = new MainPresenter(this);

        adapter = new MainPictureAdapter(data,this);

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipe_refresh_layout);
        recyclerView = (RecyclerView) findViewById(R.id.main_rv);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new ItemDecoration(ViewUtils.dip2px(this,8)));

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.loadData(loadTag);
            }
        });

        mPresenter.loadData(loadTag);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_filter:
                PopupMenu filterMenu = new PopupMenu(this,findViewById(item.getItemId()));
                filterMenu.inflate(R.menu.main_filter);
                filterMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.action_filter_school:
                                loadTag = "校花";
                                break;
                            case R.id.action_filter_fresh:
                                loadTag = "小清新";
                                break;
                        }
                        mPresenter.loadData(loadTag);
                        return false;
                    }
                });
                filterMenu.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void loadDataFinish(List<Picture> data) {
        this.data.clear();
        this.data.addAll(data);
        adapter.notifyDataSetChanged();
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onClick(View view,Picture picture) {
        Intent i = new Intent(this, PictureActivity.class);
        i.putExtra(PictureActivity.EXTRA_IMAGE_URL, picture.share_url);
        i.putExtra(PictureActivity.EXTRA_IMAGE_TITLE, picture.desc);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                this, view, PictureActivity.TRANSIT_IMG
        );
        ActivityCompat.startActivity(this, i, optionsCompat.toBundle());
    }
}
