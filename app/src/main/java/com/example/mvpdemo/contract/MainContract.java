package com.example.mvpdemo.contract;

import com.example.mvpdemo.base.BasePresenter;
import com.example.mvpdemo.base.BaseView;
import com.example.mvpdemo.model.entity.Picture;

import java.util.List;

/**
 * Created by fmw on 2016/11/28.
 */

public interface MainContract {

    interface View extends BaseView<Presenter>{

        public void loadDataFinish(List<Picture> data);

    }

    interface Presenter extends BasePresenter{

        public void loadData(String tag1);

    }

}
