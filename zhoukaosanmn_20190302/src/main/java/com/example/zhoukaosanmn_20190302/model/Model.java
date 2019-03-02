package com.example.zhoukaosanmn_20190302.model;

import com.example.zhoukaosanmn_20190302.Api.Api;
import com.example.zhoukaosanmn_20190302.Api.ApiSercive;
import com.example.zhoukaosanmn_20190302.contract.Contract;
import com.example.zhoukaosanmn_20190302.entity.BaseBean;
import com.example.zhoukaosanmn_20190302.entity.CartBean;
import com.example.zhoukaosanmn_20190302.entity.XiangqingBean;
import com.example.zhoukaosanmn_20190302.net.RetrofitCallback;
import com.example.zhoukaosanmn_20190302.util.RetrofitUtil;

import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Model implements Contract.IModel {
    @Override
    public void getCart(HashMap<String, String> map, final RetrofitCallback retrofitCallback) {
        ApiSercive apiSercive = RetrofitUtil.getInstance().setRetrofit();
        apiSercive.getCart(Api.CART_URL,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<List<CartBean>>>() {
                    @Override
                    public void accept(BaseBean<List<CartBean>> listBaseBean) throws Exception {
                        List<CartBean> result = listBaseBean.result;
                        retrofitCallback.SuccessCart(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        retrofitCallback.FailCart("购物车错误");
                    }
                });
    }

    @Override
    public void getXiangqing(HashMap<String, String> map, final RetrofitCallback retrofitCallback) {
        ApiSercive apiSercive = RetrofitUtil.getInstance().setRetrofit();
        apiSercive.getXiangqing(Api.XIANGQING_URL,map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<BaseBean<XiangqingBean>>() {
                    @Override
                    public void accept(BaseBean<XiangqingBean> listBaseBean) throws Exception {
                        XiangqingBean result = listBaseBean.result;
                        retrofitCallback.SuccessXq(result);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        retrofitCallback.FailXq("详情错误");
                    }
                });
    }
}
