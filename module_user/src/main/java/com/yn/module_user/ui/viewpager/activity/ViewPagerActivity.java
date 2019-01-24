package com.yn.module_user.ui.viewpager.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;

import com.yn.module_user.BR;
import com.yn.module_user.R;
import com.yn.module_user.databinding.FragmentViewpagerBinding;
import com.yn.module_user.ui.viewpager.vm.ViewPagerViewModel;

import me.goldze.mvvmhabit.base.BaseActivity;

/**
 * ViewPager绑定的例子, 更多绑定方式，请参考 https://github.com/evant/binding-collection-adapter
 * 所有例子仅做参考,千万不要把它当成一种标准,毕竟主打的不是例子,业务场景繁多,理解如何使用才最重要。
 * Created by goldze on 2018/7/18.
 */

public class ViewPagerActivity extends BaseActivity<FragmentViewpagerBinding, ViewPagerViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.fragment_viewpager;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }


    @Override
    public void initData() {
        // 使用 TabLayout 和 ViewPager 相关联
        binding.tabs.setupWithViewPager(binding.viewPager);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabs));
    }

    @Override
    public void initViewObservable() {
        viewModel.addPage();
    }
}
