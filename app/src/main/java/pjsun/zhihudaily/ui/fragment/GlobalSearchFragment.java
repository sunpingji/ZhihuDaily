package pjsun.zhihudaily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import pjsun.zhihudaily.R;
import pjsun.zhihudaily.business.bean.DailyResult;
import pjsun.zhihudaily.business.bean.SearchResult;
import pjsun.zhihudaily.business.bean.Story;
import pjsun.zhihudaily.business.manager.DataCallBack;
import pjsun.zhihudaily.business.manager.DataManager;
import pjsun.zhihudaily.business.search.LuceneManager;
import pjsun.zhihudaily.ui.activity.DetailActivity;
import pjsun.zhihudaily.ui.adapter.MainAdapter;
import pjsun.zhihudaily.ui.adapter.OnRecyclerViewOnClickListener;
import pjsun.zhihudaily.ui.fragment.base.BaseFragment;

/**
 * Created by sunpingji on 2017/3/15.
 */

public class GlobalSearchFragment extends BaseFragment {


    private PullLoadMoreRecyclerView recyclerView;

    private MainAdapter mainAdapter;


    private List<Story> stories;

    private SearchView searchView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initData();
    }

    private void initViews() {
        recyclerView = (PullLoadMoreRecyclerView) getView().findViewById(R.id.rv_main);
        searchView = (SearchView) getView().findViewById(R.id.search_view);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                SearchResult result = LuceneManager.getInstance().search(newText);
                stories = result.getStories();
                mainAdapter.refresh(stories);
                mainAdapter.notifyDataSetChanged();
                return true;
            }
        });

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
        recyclerView.setPullRefreshEnable(false);
        recyclerView.setHasMore(false);
    }


}
