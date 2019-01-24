package com.yn.module_market.module_service;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;

import me.goldze.mvvmhabit.provider.IChatModuleService;
import me.goldze.mvvmhabit.utils.RouteUtils;
import com.yn.module_market.net.ChatService;

/**
 * Created by Administrator on 2017/12/5 0005.
 */

@Route(path = RouteUtils.Service_Chat)
public class ChatModuleService implements IChatModuleService {

    @Override
    public String getUserName(String userId) {
        return ChatService.getUserName();
    }

    @Override
    public void init(Context context) {

    }
}
