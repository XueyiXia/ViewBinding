package com.viewbinding.fragment;

import android.os.Bundle;
import android.view.View;

import com.viewbinding.base.BaseFragment;
import com.viewbinding.databinding.FragmentUserBinding;


public class UserFragment extends BaseFragment<FragmentUserBinding> {


    @Override
    public void initView(View rootView, Bundle savedInstanceState) {
        mViewBinding.title.setText("你好个人中心，整个世界都疯了");
    }
}
