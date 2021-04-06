package com.viewbinding;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.viewbinding.databinding.ActivityMainBinding;
import com.viewbinding.fragment.HomeFragment;
import com.viewbinding.fragment.UserFragment;


public class MainActivity extends BaseActivity<ActivityMainBinding> implements View.OnClickListener {

    private static final String TAG="MainActivity";

    public final static String TAG_HOME = "TAG_HOME";   //首页

    public final static String TAG_ME = "TAG_ME";   //个人中心

    private String tag = TAG_HOME;    //标识点击了那个Fragment,默认的是定位到首页

    private HomeFragment mHomeFragment;

    private UserFragment mUserFragment;

    @Override
    public void initView(View rootView, Bundle savedInstanceState) {
        mViewBinding.maimTitle.setText("你好标题，整个世界都疯了");
        Log.e(TAG,""+rootView);
        Log.e(TAG,""+savedInstanceState);


        //首次进去显示的碎片
        commitFragment(tag);

        mViewBinding.home.setOnClickListener(this);
        mViewBinding.user.setOnClickListener(this);
    }



    /**
     * 页面切换
     * @param tag
     */
    public void commitFragment(String tag) {
        this.tag = tag;
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        switch (tag) {
            case TAG_HOME:      //首页
                if (mHomeFragment == null) {
                    mHomeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.container, mHomeFragment);
                }
                mHomeFragment.setArguments(getIntent().getExtras());
                fragmentTransaction.show(mHomeFragment);

                //个人中心隐藏
                if (mUserFragment != null && mUserFragment.isAdded()) {
                    fragmentTransaction.hide(mUserFragment);
                }
                break;

            case TAG_ME:       //个人中心
                if (mUserFragment == null) {
                    mUserFragment = new UserFragment();
                    mUserFragment.setArguments(getIntent().getExtras());
                    fragmentTransaction.add(R.id.container, mUserFragment);
                }
                fragmentTransaction.show(mUserFragment);

                //首页隐藏
                if (mHomeFragment != null && mHomeFragment.isAdded()) {
                    fragmentTransaction.hide(mHomeFragment);
                }

                break;
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home:
                commitFragment(TAG_HOME);
                break;

            case R.id.user:
                commitFragment(TAG_ME);
                break;
        }
    }
}
