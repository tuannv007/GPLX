package com.tuannv007.gplxb2.base;

import android.os.Bundle;

import com.tuannv007.gplxb2.BR;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel>
    extends AppCompatActivity {
    private T mViewDataBinding;
    private V mViewModel;

    protected int getBindingVariable() {
        return BR.viewModel;
    }

    public abstract
    @LayoutRes
    int getLayoutId();

    public V getViewModel() {
        return mViewModel;
    }

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }
}
