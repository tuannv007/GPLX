package com.tuannv007.gplxb2.base;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {
    public MutableLiveData<String> toastMessages = new MutableLiveData();
    public MutableLiveData<Boolean> isLoading = new MutableLiveData();
    public MutableLiveData<Boolean> showProgressDialog = new MutableLiveData();
    private CompositeDisposable mCompositeDisposable;

    public BaseViewModel() {
        this.mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    protected void onCleared() {
        mCompositeDisposable.dispose();
        super.onCleared();
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public void setIsLoading(boolean load) {
        isLoading.postValue(load);
    }
}