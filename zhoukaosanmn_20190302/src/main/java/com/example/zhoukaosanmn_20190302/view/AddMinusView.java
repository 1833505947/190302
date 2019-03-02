package com.example.zhoukaosanmn_20190302.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zhoukaosanmn_20190302.R;


public class AddMinusView extends LinearLayout {
    private TextView add;
    private TextView minus;
    private EditText et_num;
    private int num =1;
    public AddMinusView(Context context) {
        this(context,null);
    }

    public AddMinusView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public AddMinusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.add_minus, this, true);
        add = inflate.findViewById(R.id.add);
        minus = inflate.findViewById(R.id.minus);
        et_num = inflate.findViewById(R.id.et_num);
        et_num.setText("1");
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                num++;
                et_num.setText(num+"");
                if (addMinusCallback!=null){
                    addMinusCallback.numCallback(num);
                }
            }
        });
        minus.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                num--;
                if (num==0){
                    num=1;
                    Toast.makeText(getContext(),"不能再减了",Toast.LENGTH_SHORT).show();
                }
                et_num.setText(num+"");
                if (addMinusCallback!=null){
                    addMinusCallback.numCallback(num);
                }
            }
        });
    }
private int getNum(){
        return num;
}
    private AddMinusCallback addMinusCallback;

    public void setAddMinusCallback(AddMinusCallback addMinusCallback) {
        this.addMinusCallback = addMinusCallback;
    }

    public interface AddMinusCallback{
         void numCallback(int num);
     }

}
