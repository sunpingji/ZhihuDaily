package pjsun.alias.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import pjsun.alias.R;
import pjsun.alias.business.bean.SearchResult;
import pjsun.alias.business.bean.Story;
import pjsun.alias.business.search.LuceneManager;
import pjsun.alias.ui.activity.DetailActivity;
import pjsun.alias.ui.adapter.MainAdapter;
import pjsun.alias.ui.adapter.OnRecyclerViewOnClickListener;
import pjsun.alias.ui.fragment.base.BaseFragment;

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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) item.getActionView();
        searchView.onActionViewExpanded();
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

        super.onCreateOptionsMenu(menu, inflater);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void initViews() {
        setHasOptionsMenu(true);
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
        recyclerView.setPullRefreshEnable(false);
        recyclerView.setHasMore(false);
    }


}
