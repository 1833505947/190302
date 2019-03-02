package com.example.zhoukaosanmn_20190302.Api;

import com.example.zhoukaosanmn_20190302.entity.BaseBean;
import com.example.zhoukaosanmn_20190302.entity.CartBean;
import com.example.zhoukaosanmn_20190302.entity.XiangqingBean;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiSercive {
    @GET
    Observable<BaseBean<List<CartBean>>> getCart(@Url String url, @QueryMap HashMap<String,String> map);
    @GET
    Observable<BaseBean<XiangqingBean>> getXiangqing(@Url String url, @QueryMap HashMap<String,String> map);
}
