package com.yn.module_user.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.yn.module_user.R;

import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.bean.EventBusBean;
import me.goldze.mvvmhabit.bean.JavaBean;
import me.goldze.mvvmhabit.utils.RouteUtils;


/**
 * 依赖注入使用，注意：必须实现SerializationService进行注册，
 */
@Route(path = RouteUtils.Me_Inject)
public class InjectActivity extends AppCompatActivity {
    @Autowired
    String name = "hahahha";

    @Autowired
    int age = 13;

    @Autowired(name = "boy")//映射参数名
            boolean sex;

    @Autowired
    long high = 160;

    @Autowired
    String url;

    @Autowired
    EventBusBean pac;

    @Autowired
    JavaBean obj;

    @Autowired
    List<JavaBean> objList;

    @Autowired
    Map<String, List<JavaBean>> map;

    @Autowired
    int height = 21;//上页面没有传递

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inject);
        ARouter.getInstance().inject(this);
        initView();
    }

    private void initView() {
        mTextView = (TextView) findViewById(R.id.textView);
        String params = String.format(
                "name=%s,\n age=%s, \n height=%s,\n girl=%s,\n high=%s,\n url=%s,\n pac=%s,\n obj=%s \n" +
                        "  objList=%s, \n map=%s",
                name,
                age,
                height,
                sex,
                high,
                url,
                pac.getProject(),
                obj.getName(),
                objList.get(0).getName(),
                map.get("testMap").get(0).getName()
        );
        mTextView.setText(params);
    }
}
