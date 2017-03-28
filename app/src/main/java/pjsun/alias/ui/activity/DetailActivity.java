package pjsun.alias.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import pjsun.alias.Constant;
import pjsun.alias.R;
import pjsun.alias.business.bean.StoryDetailResult;
import pjsun.alias.business.manager.DataCallBack;
import pjsun.alias.business.manager.DataManager;
import pjsun.alias.ui.activity.base.BaseActivity;
import pjsun.alias.ui.fragment.DetailFragment;

/**
 * Created by sunpingji on 2017/3/9.
 */

public class DetailActivity extends BaseActivity {

    public static void start(Context context, String id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constant.Extra.EXTRA_ID, id);
        context.startActivity(intent);
    }

    private String id;
    private StoryDetailResult result = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initData();
    }

    private void initData() {
        id = getIntent().getExtras().getString(Constant.Extra.EXTRA_ID);
        DataManager.getInstance().getStoryDetailResult(id, new DataCallBack<StoryDetailResult>() {
            @Override
            public void onSuccess(StoryDetailResult result) {
                onLoadSuccess(result);
            }

            @Override
            public void onError() {
                onLoadError();
            }
        });


    }

    private void onLoadError() {
        loadFragment();
    }

    private void onLoadSuccess(StoryDetailResult result) {
        this.result = result;
        loadFragment();
    }

    private void loadFragment() {
        DetailFragment fragment = new DetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(Constant.Extra.EXTRA_DETAIL_RESULT, result);
        fragment.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.fl_container, fragment).commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detial_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_share:
                share();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void share() {
        if (result != null) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, result.getTitle() + result.getShareUrl());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
    }
}
