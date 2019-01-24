package com.yn.module_home;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.callback.NavCallback;
import com.alibaba.android.arouter.launcher.ARouter;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.goldze.mvvmhabit.bean.EventBusBean;
import me.goldze.mvvmhabit.bean.JavaBean;
import me.goldze.mvvmhabit.data.EvenBusTag;
import me.goldze.mvvmhabit.provider.IChatModuleService;
import me.goldze.mvvmhabit.route_service.ModuleRouteService;
import me.goldze.mvvmhabit.utils.RouteUtils;
import me.goldze.mvvmhabit.utils.UIUtils;

@Route(path = RouteUtils.Home_Fragment_Main)
public class MainFragment extends Fragment implements View.OnClickListener {

    /**
     * 登录（跨模块跳转Activity）
     */
    private Button mBtnGotoLogin;
    /**
     * 使用eventBus夸模块通信
     */
    private Button mBtnEventbus;
    /**
     * 模块间服务调用
     */
    private Button mBtnUseOtherModule;

    /**
     * 使用Uri应用内跳转
     */
    private Button mBtnUri;
    /**
     * 旧版本转场动画
     */
    private Button mOldVersionAnim;
    /**
     * 新版本转场动画
     */
    private Button mNewVersionAnim;
    /**
     * 通过URL跳转（webview）
     */
    private Button mNavByUrl;
    /**
     * 拦截器操作
     */
    private Button mInterceptor;
    /**
     * 依赖注入
     */
    private Button mAutoInject;
    /**
     * 模块间通过路径名称调用服务
     */
    private Button mBtnUseOtherModuleByName;
    /**
     * 模块间通过类名调用服务
     */
    private Button mBtnUseOtherModuleByType;
    /**
     * 跳转失败
     */
    private Button mFailNav;
    private View view;
    /**
     * 拦截器操作(利用原有分组)
     */
    private Button mInterceptor1;
    /**
     * 拦截器操作(绿色通道，跳过拦截器)
     */
    private Button mInterceptor2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        EventBus.getDefault().register(this);
        View view = inflater.inflate(R.layout.fragment_home, null);
        initView(view);
        return view;
    }

    @Override
    public void onDestroyView() {
        EventBus.getDefault().unregister(this);
        super.onDestroyView();
    }

    private void initView(View view) {
        mBtnGotoLogin = (Button) view.findViewById(R.id.btn_goto_login);
        mBtnGotoLogin.setOnClickListener(this);
        mBtnEventbus = (Button) view.findViewById(R.id.btn_eventbus);
        mBtnEventbus.setOnClickListener(this);
        mBtnUseOtherModule = (Button) view.findViewById(R.id.btn_use_other_module);
        mBtnUseOtherModule.setOnClickListener(this);
        mBtnUri = (Button) view.findViewById(R.id.btn_uri);
        mBtnUri.setOnClickListener(this);
        mOldVersionAnim = (Button) view.findViewById(R.id.oldVersionAnim);
        mOldVersionAnim.setOnClickListener(this);
        mNewVersionAnim = (Button) view.findViewById(R.id.newVersionAnim);
        mNewVersionAnim.setOnClickListener(this);
        mNavByUrl = (Button) view.findViewById(R.id.navByUrl);
        mNavByUrl.setOnClickListener(this);
        mInterceptor = (Button) view.findViewById(R.id.interceptor);
        mInterceptor.setOnClickListener(this);
        mAutoInject = (Button) view.findViewById(R.id.autoInject);
        mAutoInject.setOnClickListener(this);
        mBtnUseOtherModuleByName = (Button) view.findViewById(R.id.btn_use_other_module_by_name);
        mBtnUseOtherModuleByName.setOnClickListener(this);
        mBtnUseOtherModuleByType = (Button) view.findViewById(R.id.btn_use_other_module_by_type);
        mBtnUseOtherModuleByType.setOnClickListener(this);
        mFailNav = (Button) view.findViewById(R.id.failNav);
        mFailNav.setOnClickListener(this);
        mInterceptor1 = (Button) view.findViewById(R.id.interceptor1);
        mInterceptor1.setOnClickListener(this);
        mInterceptor2 = (Button) view.findViewById(R.id.interceptor2);
        mInterceptor2.setOnClickListener(this);
    }

    /**
     * 记住这里要使用常量表达式，因为ADT14以后在library中，去掉了final修饰，
     * google建议使用if-else代替，不用纠结
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.btn_goto_login) {
            //登录（跨模块跳转Activity）
            ARouter.getInstance().build(RouteUtils.Me_Login).navigation();
        } else if (id == R.id.btn_eventbus) {
            // 跳转并携带参数,基本涵盖所以类型传递，具体可以查看Postcard类
            //传递过去的值使用getIntent()接收
            //在fragment中无法使用ForResult进行夸模块传递数据
            //在activity中可以（详细请看LoginActivity）
            EventBusBean eventBusBean = new EventBusBean();
            eventBusBean.setProject("android");
            eventBusBean.setNum(3);

            ARouter.getInstance().build(RouteUtils.Me_EventBus)
                    .withString("name", "haungxiaoguo")
                    .withLong("age", 25)
                    .withParcelable("eventbus", eventBusBean)
                    .navigation();
        } else if (id == R.id.btn_uri) {
            EventBusBean eventBusBean = new EventBusBean();
            eventBusBean.setProject("android");
            eventBusBean.setNum(3);

            Uri testUriMix = Uri.parse("arouter://tsou.cn.huangxiaoguo/me/main/EventBus");
            ARouter.getInstance().build(testUriMix)
                    .withString("name", "haungxiaoguo")
                    .withLong("age", 25)
                    .withParcelable("eventbus", eventBusBean)
                    .navigation();

        } else if (id == R.id.oldVersionAnim) {
            //旧版本转场动画
            ARouter.getInstance()
                    .build(RouteUtils.Me_Test)
                    .withTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom)
                    .navigation(getContext());//context上下文不传无效果

        } else if (id == R.id.newVersionAnim) {
            //新版本转场动画
            if (Build.VERSION.SDK_INT >= 16) {
                ActivityOptionsCompat compat = ActivityOptionsCompat.
                        makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
                ARouter.getInstance()
                        .build(RouteUtils.Me_Test)
                        .withOptionsCompat(compat)
                        .navigation();
            } else {
                UIUtils.showToast("API < 16,不支持新版本动画");
            }
        } else if (id == R.id.navByUrl) {
            //通过URL跳转（webview）
            ARouter.getInstance()
                    .build(RouteUtils.Me_WebView)
                    .withString("url", "file:///android_asset/schame-test.html")
                    .navigation();
        } else if (id == R.id.interceptor) {
            /**
             * 如果利用重新分组，就需要在build中进行指定的分组不然没有效果
             */
            ARouter.getInstance()
                    .build(RouteUtils.Me_Test2, "needLogin")
                    .navigation(getContext(), new NavCallback() {
                        @Override
                        public void onFound(Postcard postcard) {
                            super.onFound(postcard);
                            //路由目标被发现时调用
                            Log.e("huangxiaoguo", "发现了");
                        }

                        @Override
                        public void onLost(Postcard postcard) {
                            super.onLost(postcard);
                            //路由被丢失时调用
                            Log.e("huangxiaoguo", "丢失了");
                        }

                        @Override
                        public void onArrival(Postcard postcard) {
                            //路由到达之后调用
                            Log.e("huangxiaoguo", "到达了");
                        }

                        @Override
                        public void onInterrupt(Postcard postcard) {
                            super.onInterrupt(postcard);
                            //路由被拦截时调用
                            Log.e("huangxiaoguo", "拦截了");
                        }
                    });
        } else if (id == R.id.interceptor1) {
            //拦截器操作(利用原有分组)
            ARouter.getInstance().build(RouteUtils.NeedLogin_Test3)
                    .navigation();

        } else if (id == R.id.interceptor2) {
            //拦截器操作(绿色通道，跳过拦截器)
            ARouter.getInstance().build(RouteUtils.NeedLogin_Test3)
                    .withString("extra", "我是绿色通道直接过来的，不经过拦截器")
                    .greenChannel()
                    .navigation();

        } else if (id == R.id.autoInject) {
            /**
             * 序列化过得
             * 必须先初始化JsonServiceImpl实现SerializationService
             */
            EventBusBean eventBusBean = new EventBusBean();
            eventBusBean.setProject("android");
            eventBusBean.setNum(3);
            /**
             * 普通的javaBean
             */
            JavaBean javaBean = new JavaBean();
            javaBean.setName("huangxiaoguo");
            javaBean.setAge(25);

            List<JavaBean> objList = new ArrayList<>();
            objList.add(javaBean);

            Map<String, List<JavaBean>> map = new HashMap<>();
            map.put("testMap", objList);

            ARouter.getInstance().build(RouteUtils.Me_Inject)
                    .withString("name", "老王")
                    .withInt("age", 18)
                    .withBoolean("boy", true)
                    .withLong("high", 180)
                    .withString("url", "https://www.baidu.com")
                    .withParcelable("pac", eventBusBean)
                    .withObject("obj", javaBean)
                    .withObject("objList", objList)
                    .withObject("map", map)
                    .navigation();
        } else if (id == R.id.btn_use_other_module) {
            //模块间服务调用
            //例如home模块调用chat模块的方法
            String userName = ModuleRouteService.getUserName("userId");
            UIUtils.showToast(userName);
        } else if (id == R.id.btn_use_other_module_by_name) {
            //模块间通过路径名称调用服务
            String userName = ((IChatModuleService) ARouter.getInstance()
                    .build(RouteUtils.Service_Chat)
                    .navigation())
                    .getUserName("userid");
            UIUtils.showToast(userName);
        } else if (id == R.id.btn_use_other_module_by_type) {
            //模块间通过类名调用服务
            String userName = ARouter.getInstance()
                    .navigation(IChatModuleService.class)
                    .getUserName("userid");
            UIUtils.showToast(userName);
        } else if (id == R.id.failNav) {
            //跳转失败
            ARouter.getInstance().build("/xxx/xxx").navigation(getContext(), new NavCallback() {
                @Override
                public void onFound(Postcard postcard) {
                    UIUtils.showToast("找到了");
                }

                @Override
                public void onLost(Postcard postcard) {
                    UIUtils.showToast("找不到了");
                }

                @Override
                public void onArrival(Postcard postcard) {
                    UIUtils.showToast("跳转完了");
                }

                @Override
                public void onInterrupt(Postcard postcard) {
                    UIUtils.showToast("被拦截了");
                }
            });
        }
    }

    @Subscriber(tag = EvenBusTag.GOTO_EVENTBUS)
    public void onEvent(String s) {
        UIUtils.showToast(s);
    }

}