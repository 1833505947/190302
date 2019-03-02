package com.example.zhoukaosanmn_20190302.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.zhoukaosanmn_20190302.R;
import com.example.zhoukaosanmn_20190302.adapter.CartAdapter;
import com.example.zhoukaosanmn_20190302.contract.Contract;
import com.example.zhoukaosanmn_20190302.entity.CartBean;
import com.example.zhoukaosanmn_20190302.entity.XiangqingBean;
import com.example.zhoukaosanmn_20190302.presenter.Presenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cartfragment extends Fragment implements Contract.IView ,CartAdapter.CartUICallback {
    //@BindView(R.id.xre)
    XRecyclerView xre;
    private Presenter presenter;
    private CheckBox checkbox;
    private List<CartBean> cart;
    private CartAdapter cartAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.cartfragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //ButterKnife.bind(view);
        xre = view.findViewById(R.id.xre);
        checkbox = view.findViewById(R.id.checkbox);
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()) {
                    for (CartBean cartBean : cart) {
                        cartBean.isChecked = true;
                    }
                }else {
                    for (CartBean cartBean : cart) {
                        cartBean.isChecked = false;
                    }
                }
                cartAdapter.notifyDataSetChanged();
                Price();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        cart = new ArrayList<>();
        presenter = new Presenter(this);
        HashMap<String, String> map = new HashMap<>();
        map.put("labelId","1003");
        map.put("page","1");
        map.put("count","10");
        presenter.getCart(map);
    }

    @Override
    public void SuccessCart(List<CartBean> list) {
        //Toast.makeText(getActivity(),list.size()+"购物车请求成功！",Toast.LENGTH_SHORT).show();
        cart = list;
        xre.setLayoutManager(new LinearLayoutManager(getActivity()));
        cartAdapter = new CartAdapter(getActivity(), cart);
        cartAdapter.setCartUICallback(this);
        xre.setAdapter(cartAdapter);
    }

    @Override
    public void FailCart(String msg) {
        Toast.makeText(getActivity(),"购物车请求失败！",Toast.LENGTH_SHORT).show();


    }

    @Override
    public void SuccessXiangqing(XiangqingBean list) {
        Toast.makeText(getActivity(),"详情请求成功！",Toast.LENGTH_SHORT).show();

    }



    @Override
    public void FailXiangqing(String msg) {
        Toast.makeText(getActivity(),"详情请求失败！",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void notifyCart() {
        Price();
    }
    public void Price(){
        double p = 0;

        for (CartBean cartBean : cart) {
            if (cartBean.isChecked){
                p+=cartBean.price*cartBean.saleNum;
            }
        }
        checkbox.setText("￥："+p);
    }
}
