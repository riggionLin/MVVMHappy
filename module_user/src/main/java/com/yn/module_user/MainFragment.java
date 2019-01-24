package com.yn.module_user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.yn.module_user.databinding.FragmentMainTestBinding;
import com.yn.module_user.databinding.FragmentTabBar1Binding;
import com.yn.module_user.ui.login.LoginViewModel;

import me.goldze.mvvmhabit.base.BaseFragment;
import me.goldze.mvvmhabit.utils.RouteUtils;
import com.yn.module_user.BR;
import com.yn.module_user.ui.login.MainViewModel;

/**
 * Created by goldze on 2018/7/18.
 */
@Route(path = RouteUtils.Me_Fragment_Main)
public class MainFragment extends BaseFragment<FragmentMainTestBinding,MainViewModel>{
    @Override
    public int initContentView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return R.layout.fragment_main_test;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }



}
