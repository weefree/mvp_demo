package com.example.mvpdemo.model.net.resp;

import com.example.mvpdemo.model.entity.Picture;

import java.util.List;

/**
 * Created by fmw on 2016/11/29.
 */

public class RespBody <T> {
    public String tag1;
    public String tag2;
    public int  totalNum;
    public int start_index;
    public int return_number;
    public List<T> data;
}
