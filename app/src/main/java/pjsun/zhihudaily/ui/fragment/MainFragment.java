package pjsun.zhihudaily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.TimeUtils;
import com.orhanobut.logger.Logger;

import java.util.List;

import pjsun.zhihudaily.R;
import pjsun.zhihudaily.business.bean.NewsResult;
import pjsun.zhihudaily.business.bean.Story;
import pjsun.zhihudaily.business.manager.DataCallBack;
import pjsun.zhihudaily.business.manager.DataManager;
import pjsun.zhihudaily.ui.activity.DetailActivity;
import pjsun.zhihudaily.ui.adapter.MainAdapter;
import pjsun.zhihudaily.ui.adapter.OnRecyclerViewOnClickListener;
import pjsun.zhihudaily.ui.fragment.base.BaseFragment;

/**
 * Created by sunpingji on 2017/3/8.
 */

public class MainFragment extends BaseFragment {

    private DataManager dataManager;

    private RecyclerView recyclerView;

    private MainAdapter mainAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initData();
    }

    private void initViews() {
        recyclerView = (RecyclerView) getView().findViewById(R.id.rv_main);
    }

    private void initData() {
        dataManager = new DataManager(getActivity());
        loadData();
    }

    private void loadData() {
        dataManager.getNewsResult(null,new DataCallBack<NewsResult>() {
            @Override
            public void onSuccess(NewsResult result) {
                onLoadSuccess(result);
            }

            @Override
            public void onError() {
                onLoadError();
            }
        });
    }

    private void onLoadError() {

    }

    private void onLoadSuccess(final NewsResult result) {
        if (result.getStories() == null || result.getStories().size() == 0) {
            onLoadError();
        } else {
            if (mainAdapter == null) {
                mainAdapter = new MainAdapter(getActivity(), result.getStories(), new OnRecyclerViewOnClickListener() {
                    @Override
                    public void OnItemClick(View v, int position) {
                        Story story = result.getStories().get(position);
                        if (story != null) {
                            DetailActivity.start(getActivity(), story.getId());
                        }
                    }
                });
                recyclerView.setAdapter(mainAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            } else {
                mainAdapter.refresh(result.getStories());
            }
        }
    }
}
