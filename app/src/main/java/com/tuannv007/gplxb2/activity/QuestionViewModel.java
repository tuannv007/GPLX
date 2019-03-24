package com.tuannv007.gplxb2.activity;

import com.tuannv007.gplxb2.base.BaseViewModel;

import androidx.lifecycle.MutableLiveData;

public class QuestionViewModel extends BaseViewModel {
    public MutableLiveData<String> titleToolbar = new MutableLiveData<>();
    public MutableLiveData<String> totalQuestion = new MutableLiveData<>();

    public QuestionViewModel() {
    }
}
