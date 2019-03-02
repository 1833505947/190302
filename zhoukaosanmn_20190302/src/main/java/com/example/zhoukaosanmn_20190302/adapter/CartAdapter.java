package com.example.zhoukaosanmn_20190302.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.zhoukaosanmn_20190302.R;
import com.example.zhoukaosanmn_20190302.XiangqingActivity;
import com.example.zhoukaosanmn_20190302.entity.CartBean;
import com.example.zhoukaosanmn_20190302.view.AddMinusView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

public class CartAdapter extends XRecyclerView.Adapter<CartAdapter.ViewHolder> {
   private Context context;
   private List<CartBean> list;
    private CartUICallback cartUICallback;

    public void setCartUICallback(CartUICallback cartUICallback) {
        this.cartUICallback = cartUICallback;
    }
    public interface CartUICallback {
        void notifyCart();
    }
    public CartAdapter(Context context, List<CartBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cart_layout, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(inflate);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final CartBean cartBean = list.get(i);
        viewHolder.title.setText(cartBean.commodityName);
        viewHolder.price.setText(cartBean.price+"");
        viewHolder.iv_product.setImageURI(cartBean.masterPic);
        viewHolder.checkbox.setChecked(cartBean.isChecked);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,XiangqingActivity.class);
                intent.putExtra("id",cartBean.commodityId);
                context.startActivity(intent);
            }
        });
        viewHolder.checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartBean.isChecked = viewHolder.checkbox.isChecked();
                notifyDataSetChanged();
                if (cartUICallback!=null){
                    cartUICallback.notifyCart();
                }
            }
        });
        viewHolder.addminusView.setAddMinusCallback(new AddMinusView.AddMinusCallback() {
            @Override
            public void numCallback(int num) {
                cartBean.saleNum = num;
                if (cartUICallback!=null){
                    cartUICallback.notifyCart();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        CheckBox checkbox;
        SimpleDraweeView iv_product;
        TextView title,price;
        AddMinusView addminusView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
            iv_product = itemView.findViewById(R.id.iv_product);
            title = itemView.findViewById(R.id.title);
            price = itemView.findViewById(R.id.price);
            addminusView = itemView.findViewById(R.id.addminusView);
        }
    }
}
