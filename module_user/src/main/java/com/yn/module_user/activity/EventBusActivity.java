package com.yn.module_user.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yn.module_user.R;

import org.simple.eventbus.EventBus;

import me.goldze.mvvmhabit.bean.EventBusBean;
import me.goldze.mvvmhabit.data.EvenBusTag;
import me.goldze.mvvmhabit.utils.RouteUtils;


@Route(path = RouteUtils.Me_EventBus)
public class EventBusActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * eventBus数据接收页面
     */
    private TextView mTextView;
    /**
     * eventBus返回数据
     */
    private Button mBtnBackData;

    private String name;

    private long age;
    private EventBusBean eventbus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        ARouter.getInstance().inject(this);
        initData();
        initView();
    }

    private void initData() {
        name = getIntent().getStringExtra("name");
        age = getIntent().getLongExtra("age", 0);
        eventbus = getIntent().getParcelableExtra("eventbus");
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        mBtnBackData = (Button) findViewById(R.id.btn_back_data);
        mBtnBackData.setOnClickListener(this);
        mTextView.setText("name=" + name + ",\tage=" + age + ",\tproject=" + eventbus.getProject() +
                ",\tnum=" + eventbus.getNum());
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btn_back_data) {
            EventBus.getDefault().post(name, EvenBusTag.GOTO_EVENTBUS);
            finish();
        } else {
        }
    }
}
