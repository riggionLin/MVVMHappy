package com.yn.module_market.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import me.goldze.mvvmhabit.utils.RouteUtils;
import com.yn.module_market.R;

@Route(path = RouteUtils.Chat_Interceptor)
public class InterceptorActivity extends AppCompatActivity {

    /**
     * eventBus数据接收页面
     */
    private TextView mTextView;
    private String extra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interceptor);
        initData();
        initView();
    }

    private void initData() {
        extra = getIntent().getStringExtra("extra");

    }
    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mTextView.setText("extra==>" + extra);
    }
}
