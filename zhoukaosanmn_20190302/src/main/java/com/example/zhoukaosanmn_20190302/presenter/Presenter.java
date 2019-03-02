package com.example.zhoukaosanmn_20190302.presenter;

import com.example.zhoukaosanmn_20190302.contract.Contract;
import com.example.zhoukaosanmn_20190302.entity.CartBean;
import com.example.zhoukaosanmn_20190302.entity.XiangqingBean;
import com.example.zhoukaosanmn_20190302.model.Model;
import com.example.zhoukaosanmn_20190302.net.RetrofitCallback;

import java.util.HashMap;
import java.util.List;

public class Presenter extends Contract.IPresenter {
    private Model model;
    private Contract.IView iView;

    public Presenter(Contract.IView iView) {
        this.model = new Model();
        this.iView = iView;
    }

    @Override
    public void getCart(HashMap<String, String> map) {
        if (model!=null){
            model.getCart(map, new RetrofitCallback() {
                @Override
                public void SuccessCart(List<CartBean> list) {
                    if (iView!=null){
                        iView.SuccessCart(list);
                    }
                }

                @Override
                public void FailCart(String msg) {
                    if (iView!=null){
                        iView.FailCart(msg);
                   }
                }

                @Override
                public void SuccessXq(XiangqingBean list) {

                }

                @Override
                public void FailXq(String msg) {

                }
            });
        }
    }

    @Override
    public void getXiangqing(HashMap<String, String> map) {
        if (model!=null) {
            model.getXiangqing(map, new RetrofitCallback() {
                @Override
                public void SuccessCart(List<CartBean> list) {

                }

                @Override
                public void FailCart(String msg) {

                }

                @Override
                public void SuccessXq(XiangqingBean list) {
                    if (iView != null) {
                        iView.SuccessXiangqing(list);
                    }
                }

                @Override
                public void FailXq(String msg) {
                    if (iView != null) {
                        iView.FailXiangqing(msg);
                    }
                }
            });
        }}
}
