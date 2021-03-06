package pjsun.alias.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;


import java.util.ArrayList;
import java.util.List;

import pjsun.alias.R;
import pjsun.alias.business.bean.DailyResult;
import pjsun.alias.business.bean.Story;
import pjsun.alias.business.manager.DataCallBack;
import pjsun.alias.business.manager.DataManager;
import pjsun.alias.ui.activity.DetailActivity;
import pjsun.alias.ui.adapter.MainAdapter;
import pjsun.alias.ui.adapter.OnRecyclerViewOnClickListener;
import pjsun.alias.ui.fragment.base.BaseFragment;
import pjsun.alias.utils.ZhihuDateUtils;

/**
 * Created by sunpingji on 2017/3/8.
 */

public class MainFragment extends BaseFragment {


    private PullLoadMoreRecyclerView recyclerView;

    private MainAdapter mainAdapter;

    private String date;

    private List<Story> stories;

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
        recyclerView = (PullLoadMoreRecyclerView) getView().findViewById(R.id.rv_main);
    }

    private void initData() {
        stories = new ArrayList<Story>();
        mainAdapter = new MainAdapter(getActivity(), stories, new OnRecyclerViewOnClickListener() {
            @Override
            public void OnItemClick(View v, int position) {
                Story story = stories.get(position);
                if (story != null) {
                    DetailActivity.start(getActivity(), story.getId());
                }
            }
        });
        recyclerView.setAdapter(mainAdapter);
        recyclerView.setLinearLayout();
        recyclerView.setOnPullLoadMoreListener(loadMoreListener);
        loadData();
    }

    private void loadData() {
        DataManager.getInstance().getDailyResult(date, new DataCallBack<DailyResult>() {
            @Override
            public void onSuccess(DailyResult result) {
                onLoadSuccess(result);
            }

            @Override
            public void onError() {
                onLoadError();
            }
        });
    }

    private void onLoadError() {
        recyclerView.setRefreshing(false);
        recyclerView.setPullLoadMoreCompleted();
        if (stories == null || stories.size() == 0) {
            showErrorNoDataPage();
        }
    }

    private void showErrorNoDataPage() {

    }

    private void onLoadSuccess(final DailyResult result) {
        recyclerView.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setPullLoadMoreCompleted();
                if (isResultValid(result)) {
                    String newDate = result.getDate();
                    List<Story> newStories = result.getStories();
                    if (date == null || ZhihuDateUtils.isToday(newDate)) {
                        stories = newStories;
                    } else {
                        stories.addAll(newStories);
                    }
                    date = newDate;
                    mainAdapter.refresh(stories);
                } else {
                    onLoadError();
                }
            }
        });
    }

    private boolean isResultValid(DailyResult result) {
        if (result == null || result.getStories() == null || result.getStories().size() == 0) {
            return false;
        } else {
            return true;
        }
    }

    private PullLoadMoreRecyclerView.PullLoadMoreListener loadMoreListener = new PullLoadMoreRecyclerView.PullLoadMoreListener() {
        @Override
        public void onRefresh() {
            loadData();
        }

        @Override
        public void onLoadMore() {
            loadData();
        }
    };
}
