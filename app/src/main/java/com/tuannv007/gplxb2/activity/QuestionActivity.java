package com.tuannv007.gplxb2.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.tuannv007.gplxb2.R;
import com.tuannv007.gplxb2.base.BaseFragment;
import com.tuannv007.gplxb2.data.model.Question;
import com.tuannv007.gplxb2.database.DatabaseAccess;
import com.tuannv007.gplxb2.databinding.ActivityQuestionBinding;
import com.tuannv007.gplxb2.recycleview.BaseViewAdapter;
import com.tuannv007.gplxb2.recycleview.BindingViewHolder;
import com.tuannv007.gplxb2.recycleview.SingleTypeAdapter;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

public class QuestionActivity extends BaseFragment<ActivityQuestionBinding, QuestionViewModel>
        implements BaseViewAdapter.Listener {
    private QuestionViewModel viewModel;
    private SharedPreferences sharedpreferences;
    private InterstitialAd mInterstitialAd;
    public static final String MyPREFERENCES = "MyPrefs";

    public static QuestionActivity newInstance(int categoryId, String title) {
        Bundle args = new Bundle();
        args.putInt("CATEGORY_ID", categoryId);
        args.putString("TITLE", title);
        QuestionActivity fragment = new QuestionActivity();
        fragment.setArguments(args);
        return fragment;
    }

    private int getCategoryId() {
        if (getArguments() == null) return 0;
        return getArguments().getInt("CATEGORY_ID");
    }

    private String getTitle() {
        if (getArguments() == null) return "";
        return getArguments().getString("TITLE");
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_question;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        initAdapter();
    }

    private void initView(View view) {
        viewModel = new QuestionViewModel();
        getViewDataBinding().setViewModel(viewModel);
        sharedpreferences = view.getContext().getSharedPreferences(MyPREFERENCES, Context
                .MODE_PRIVATE);
        getViewDataBinding().icBack.setOnClickListener(v -> {
            getActivity().onBackPressed();
        });
    }

    private void initAdapter() {
        SingleTypeAdapter<Question> adapter =
                new SingleTypeAdapter<>(getActivity(), R.layout.item_question);
        LinearLayoutManager manager =
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL,
                        false);
        List<Question> questionTypeList =
                DatabaseAccess.getInstance(getActivity()).getQuestion(getCategoryId());
        adapter.set(questionTypeList);
        getViewDataBinding().recyclerView.setLayoutManager(manager);
        getViewDataBinding().recyclerView.setAdapter(adapter);
        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(getViewDataBinding().recyclerView);
        adapter.setPresenter(this);
        adapter.setDecorator((holder, position, viewType) -> {
            holder.itemView.findViewById(R.id.sub).setVisibility(position == 0 ? View
                    .VISIBLE : View.GONE);
        });
        int categoryId = sharedpreferences.getInt("categoryId", 0);
        int index = sharedpreferences.getInt("index", 0);
        if (categoryId == getCategoryId()) {
            viewModel.titleToolbar.postValue(index + 1 + "/" + adapter.getItemCount() + " " +
                    getTitle());
            getViewDataBinding().recyclerView.scrollToPosition(index);
        } else
            viewModel.titleToolbar.postValue(1 + "/" + adapter.getItemCount() + " " + getTitle());
        getViewDataBinding().recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                viewModel.titleToolbar.postValue(
                        (manager.findFirstVisibleItemPosition() + 1) + "/" + adapter.getItemCount() +
                                " " + getTitle());
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putInt("categoryId", getCategoryId());
                editor.putInt("index", manager.findFirstVisibleItemPosition());
                editor.apply();
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }
}
