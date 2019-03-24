package com.tuannv007.gplxb2.fragment.fragmenttest;

import android.os.Bundle;
import android.view.View;

import com.tuannv007.gplxb2.R;
import com.tuannv007.gplxb2.activity.QuestionActivity;
import com.tuannv007.gplxb2.base.BaseFragment;
import com.tuannv007.gplxb2.data.model.Category;
import com.tuannv007.gplxb2.database.DatabaseAccess;
import com.tuannv007.gplxb2.databinding.FragmentTestBinding;
import com.tuannv007.gplxb2.notification.AlarmReceiver;
import com.tuannv007.gplxb2.notification.NotificationScheduler;
import com.tuannv007.gplxb2.recycleview.BaseViewAdapter;
import com.tuannv007.gplxb2.recycleview.SingleTypeAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;

public class FragmentTest extends BaseFragment<FragmentTestBinding, FragmentTestViewModel>
    implements BaseViewAdapter.Listener {
    public static FragmentTest newInstance() {
        Bundle args = new Bundle();
        FragmentTest fragment = new FragmentTest();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_test;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initAdapter();
        NotificationScheduler.setReminder(getActivity(), AlarmReceiver.class, 20, 10);
    }

    private void initAdapter() {
        SingleTypeAdapter<Category> adapter =
            new SingleTypeAdapter<>(getActivity(), R.layout.item_test);
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2);
        List<Category> categoryList =
            DatabaseAccess.getInstance(getActivity()).getZQuestionType();
        adapter.set(categoryList);
        getViewDataBinding().recyclerView.setLayoutManager(manager);
        getViewDataBinding().recyclerView.setAdapter(adapter);
        adapter.setPresenter(this);
    }

    public void clickItem(Category category) {
        addFragment(QuestionActivity.newInstance(category.getzId(), category.getName()),
            R.id.frame_layout);
    }
}
