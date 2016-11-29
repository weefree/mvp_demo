package com.example.mvpdemo.presenter;

import com.example.mvpdemo.contract.MainContract;
import com.example.mvpdemo.model.entity.Picture;
import com.example.mvpdemo.model.net.NetClient;
import com.example.mvpdemo.model.net.resp.RespBody;
import com.example.mvpdemo.utils.Logger;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

/**
 * Created by fmw on 2016/11/28.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View mView;

    public MainPresenter(MainContract.View view) {
        this.mView = view;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void loadData(String tag2) {
        HashMap<String, String> querys = new HashMap<>();
        querys.put("pn", "0");
        querys.put("rn", "30");
        querys.put("tag1", "美女");
        querys.put("tag2", tag2);
        querys.put("ie", "utf8");
        NetClient.getApi().getPictures(querys)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RespBody<Picture>>() {
                    @Override
                    public void call(RespBody<Picture> pictureRespBody) {
                        if (pictureRespBody != null && pictureRespBody.data != null) {
                            mView.loadDataFinish(pictureRespBody.data);
                        }
                    }
                });

    }
}
