package com.example.zhoukaosanmn_20190302.contract;

import com.example.zhoukaosanmn_20190302.entity.CartBean;
import com.example.zhoukaosanmn_20190302.entity.XiangqingBean;
import com.example.zhoukaosanmn_20190302.net.RetrofitCallback;

import java.util.HashMap;
import java.util.List;

public interface Contract {
    public abstract class IPresenter{
        public abstract void getCart(HashMap<String,String> map);
        public abstract void getXiangqing(HashMap<String,String> map);
    }
    interface IModel{
        void getCart(HashMap<String,String> map, RetrofitCallback retrofitCallback);
        void getXiangqing(HashMap<String,String> map,RetrofitCallback retrofitCallback);
    }
    interface IView{
        void SuccessCart(List<CartBean> list);
        void FailCart(String msg);
        void SuccessXiangqing(XiangqingBean list);
        void FailXiangqing(String msg);
    }
}
