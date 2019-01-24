package me.goldze.mvvmhabit;



import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import me.goldze.mvvmhabit.utils.UIUtils;
import me.goldze.mvvmhabit.base.BaseApplication;
import me.goldze.mvvmhabit.utils.KLog;
import me.goldze.mvvmhabit.crash.CaocConfig;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

public class MyApplication extends Application {
    /**
     * 上下文
     */
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        KLog.init(BuildConfig.DEBUG);
        initRouter(this);
        initCrash();
        BaseApplication.setApplication(this);

    }
    public static Context getInstance() {
        return instance;
    }

    private void initCrash() {
        me.goldze.mvvmhabit.crash.CaocConfig.Builder.create()
                .backgroundMode(CaocConfig.BACKGROUND_MODE_SILENT) //背景模式,开启沉浸式
                .enabled(true) //是否启动全局异常捕获
                .showErrorDetails(true) //是否显示错误详细信息
                .showRestartButton(true) //是否显示重启按钮
                .trackActivities(true) //是否跟踪Activity
                .minTimeBetweenCrashesMs(2000) //崩溃的间隔时间(毫秒)
                .errorDrawable(R.mipmap.ic_launcher) //错误图标
//  .restartActivity(LoginActivity.class) //重新启动后的activity
//                .errorActivity(YourCustomErrorActivity.class) //崩溃后的错误activity
//                .eventListener(new YourCustomEventListener()) //崩溃后的错误监听
                .apply();
    }

    private void initRouter(MyApplication mApplication) {
        // 这两行必须写在init之前，否则这些配置在init过程中将无效
        if (UIUtils.isApkInDebug(instance)) {
            //打印日志
            ARouter.openLog();
            //开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！
            //线上版本需要关闭,否则有安全风险)
            ARouter.openDebug();
        }
        // 尽可能早，推荐在Application中初始化
        ARouter.init(mApplication);


    }
}
