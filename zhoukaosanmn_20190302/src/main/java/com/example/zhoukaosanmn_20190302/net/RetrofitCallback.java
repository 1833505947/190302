package com.example.zhoukaosanmn_20190302.net;

import com.example.zhoukaosanmn_20190302.entity.CartBean;
import com.example.zhoukaosanmn_20190302.entity.XiangqingBean;

import java.util.List;

public interface RetrofitCallback {
    void SuccessCart(List<CartBean> list);
    void FailCart(String msg);
    void SuccessXq(XiangqingBean list);
    void FailXq(String msg);
}
