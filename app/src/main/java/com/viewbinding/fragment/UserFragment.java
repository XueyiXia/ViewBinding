package com.viewbinding.fragment;

import android.os.Bundle;
import android.view.View;


public class UserFragment extends BaseFragment<com.viewbinding.databinding.FragmentUserBinding> {


    @Override
    public void initView(View rootView, Bundle savedInstanceState) {
        mViewBinding.title.setText("你好个人中心，整个世界都疯了");
    }
}
