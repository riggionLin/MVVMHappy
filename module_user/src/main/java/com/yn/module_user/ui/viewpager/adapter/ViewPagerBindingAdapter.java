package com.yn.module_user.ui.viewpager.adapter;

import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.Observer;
import android.databinding.ViewDataBinding;
import android.support.annotation.Nullable;
import android.view.ViewGroup;

import com.yn.module_user.databinding.ItemViewpagerBinding;
import com.yn.module_user.ui.viewpager.vm.ViewPagerItemViewModel;

import me.goldze.mvvmhabit.utils.ToastUtils;
import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;

/**
 * Created by goldze on 2018/6/21.
 */

public class ViewPagerBindingAdapter extends BindingViewPagerAdapter<ViewPagerItemViewModel> {

    @Override
    public void onBindBinding(final ViewDataBinding binding, int variableId, int layoutRes, final int position, ViewPagerItemViewModel item) {
        super.onBindBinding(binding, variableId, layoutRes, position, item);
        //这里可以强转成ViewPagerItemViewModel对应的ViewDataBinding，
        ItemViewpagerBinding _binding = (ItemViewpagerBinding) binding;
        item.clickEvent.observe((LifecycleOwner) _binding.getRoot().getContext(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                ToastUtils.showShort("position：" + position);
            }
        });
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }
}
