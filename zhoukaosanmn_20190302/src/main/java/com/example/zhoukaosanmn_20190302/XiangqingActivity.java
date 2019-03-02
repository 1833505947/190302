package com.example.zhoukaosanmn_20190302;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.zhoukaosanmn_20190302.contract.Contract;
import com.example.zhoukaosanmn_20190302.entity.CartBean;
import com.example.zhoukaosanmn_20190302.entity.XiangqingBean;
import com.example.zhoukaosanmn_20190302.presenter.Presenter;
import com.stx.xhb.xbanner.XBanner;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class XiangqingActivity extends AppCompatActivity implements Contract.IView {

    private XBanner xbanner;
    private TextView name,price;
    private Presenter presenter;
    private List<String> banner1;
    private WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqing);
        xbanner =  findViewById(R.id.xbanner);
        name =  findViewById(R.id.name);
        price =  findViewById(R.id.price);
        webview = findViewById(R.id.webview);
        initData();
    }

    private void initData() {
        presenter = new Presenter(this);
        //EventBus.getDefault().register(this);
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        Toast.makeText(this,id,Toast.LENGTH_SHORT).show();
        HashMap<String,String> map = new HashMap<>();
        map.put("commodityId",id);
        presenter.getXiangqing(map);

    }
   /* @Subscribe
    public void receiver(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();

    }*/

    @Override
    public void SuccessCart(List<CartBean> list) {

    }

    @Override
    public void FailCart(String msg) {

    }

    @Override
    public void SuccessXiangqing(XiangqingBean list) {
        banner1 = new ArrayList<>();
        String picture = list.picture;
        String[] split = picture.split(",");
        for (int i=0;i<split.length;i++){
            banner1.add(split[i]);
        }
        xbanner.setData(banner1,null);
        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Glide.with(XiangqingActivity.this).load(banner1.get(position)).into((ImageView) view);
            }
        });
        name.setText(list.commodityName);
        price.setText(list.price);
        webview.setWebChromeClient(new WebChromeClient());
        webview.loadData(list.details,"text/html","utf-8");


        Toast.makeText(this,"详情请求成功！",Toast.LENGTH_SHORT).show();
    }



    @Override
    public void FailXiangqing(String msg) {
        //Toast.makeText(this,"详情请求失败！",Toast.LENGTH_SHORT).show();
    }
}
