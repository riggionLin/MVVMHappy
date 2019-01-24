package com.yn.module_market.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import me.goldze.mvvmhabit.utils.RouteUtils;
import com.yn.module_market.R;

@Route(path = RouteUtils.Chat_ForResult)
public class ForResultActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * finish
     */
    private Button mFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_result);
        initView();
    }

    private void initView() {
        mFinish = (Button) findViewById(R.id.finish);
        mFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.finish) {
            Intent intent = new Intent();
            intent.putExtra("name", "ForResult返回的数据");
            setResult(999, intent);
            finish();
        } else {
        }
    }
}
