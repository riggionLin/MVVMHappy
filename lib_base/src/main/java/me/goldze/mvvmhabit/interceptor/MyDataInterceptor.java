package me.goldze.mvvmhabit.interceptor;

import android.content.Context;
import android.util.Log;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.annotation.Interceptor;
import com.alibaba.android.arouter.facade.callback.InterceptorCallback;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.launcher.ARouter;

import me.goldze.mvvmhabit.utils.RouteUtils;

/**
 * Created by Administrator on 2017/12/6 0006.
 * 添加拦截器的时候，建议clean再打包运行，不然会出现，无效的情况
 * <p>
 * 切记一个项目里面priority的值保证唯一，不然会出毛病
 */
@Interceptor(priority = 1, name = "重新分组进行拦截")
public class MyDataInterceptor implements IInterceptor {

    @Override
    public void process(Postcard postcard, InterceptorCallback callback) {
        if (postcard.getGroup().equals("needLogin")) {
            Log.e("huangxiaoguo", "需要去登陆");
            //直接执行
            // callback.onContinue(postcard);

            //直接拦截,走onLost方法
            //  callback.onInterrupt(null);

            //添加数据
//        postcard.withString("extra", "我是在拦截器中附加的参数");
//        callback.onContinue(postcard);
            callback.onInterrupt(null);
            ARouter.getInstance().build(RouteUtils.Me_Login)
                    .withString("path", postcard.getPath()).navigation();
        } else {
            postcard.withString("extra", "我是在拦截器中附加的参数");
            callback.onContinue(postcard);
        }
    }

    @Override
    public void init(Context context) {

    }
}
