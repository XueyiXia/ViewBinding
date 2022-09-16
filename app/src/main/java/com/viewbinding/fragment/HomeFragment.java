package com.viewbinding.fragment;

import android.os.Bundle;
import android.view.View;

import com.viewbinding.base.BaseFragment;
import com.viewbinding.databinding.FragmentHomeBinding;

public class HomeFragment extends BaseFragment<FragmentHomeBinding> {




    @Override
    public void initView(View rootView, Bundle savedInstanceState) {

        mViewBinding.title.setText("你好首页，整个世界都疯了");
    }
}
