package com.viewbinding.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
public abstract class BaseFragment<T extends ViewBinding>extends Fragment {

    public T mViewBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Type superclass = getClass().getGenericSuperclass();
        if(superclass!=null){
            Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
            try {
                Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class,ViewGroup.class,boolean.class);
                mViewBinding = (T) method.invoke(null, getLayoutInflater(),container,false);
            } catch (Exception  e) {
                e.printStackTrace();
            }
        }

        return mViewBinding!=null?mViewBinding.getRoot():null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(mViewBinding.getRoot(),savedInstanceState);
    }

    /**
     * 入口函数
     */
    abstract public void initView(View rootView, Bundle savedInstanceState);
}
