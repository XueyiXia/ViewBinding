package com.viewbinding.base;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewbinding.ViewBinding;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class BaseActivity <T extends ViewBinding>extends AppCompatActivity {

    public T mViewBinding;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Type superclass = getClass().getGenericSuperclass();
        if(superclass!=null){
            Class<?> aClass = (Class<?>) ((ParameterizedType) superclass).getActualTypeArguments()[0];
            try {
                Method method = aClass.getDeclaredMethod("inflate", LayoutInflater.class);
                mViewBinding = (T) method.invoke(null, getLayoutInflater());
                if(mViewBinding!=null){
                    setContentView(mViewBinding.getRoot());
                }

            } catch (NoSuchMethodException | IllegalAccessException| InvocationTargetException e) {
                e.printStackTrace();
            }
        }


        initView(getWindow().getDecorView(), savedInstanceState);
    }


    /**
     * 入口函数
     */
    abstract public void initView(View rootView, Bundle savedInstanceState);
}
