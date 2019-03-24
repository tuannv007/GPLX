package com.tuannv007.gplxb2.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tuannv007.gplxb2.base.BaseViewModel;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {
    private T mViewDataBinding;
    private V mViewModel;
    private View mRootView;

    public int getBindingVariable() {
        return com.tuannv007.gplxb2.BR.viewModel;
    }

    public V getViewModel() {
        return mViewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setLifecycleOwner(this);
        mViewDataBinding.executePendingBindings();
    }

    public abstract
    @LayoutRes
    int getLayoutId();

    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    public void addFragment(Fragment frg, int layoutId) {
        String tag = frg.getClass().getSimpleName();
        getFragmentManager().beginTransaction().replace(layoutId, frg, tag).addToBackStack(tag)
                .commit();
    }
}
