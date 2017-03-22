package pjsun.zhihudaily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import org.litepal.crud.DataSupport;

import java.util.List;

import pjsun.zhihudaily.Constant;
import pjsun.zhihudaily.R;
import pjsun.zhihudaily.business.bean.DailyResult;
import pjsun.zhihudaily.business.bean.Story;
import pjsun.zhihudaily.business.bean.StoryDetailResult;
import pjsun.zhihudaily.ui.activity.base.BaseActivity;
import pjsun.zhihudaily.ui.fragment.DetailFragment;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO;
import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES;

/**
 * Created by sunpingji on 2017/3/9.
 */

public class DetailActivity extends BaseActivity {

    public static void start(Context context, String id) {
        Intent intent = new Intent(context, DetailActivity.class);
        intent.putExtra(Constant.Extra.EXTRA_ID, id);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initData();
    }

    private void initData() {
        getFragmentManager().beginTransaction().replace(R.id.fl_container, new DetailFragment()).commitAllowingStateLoss();
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
        List<StoryDetailResult> list = DataSupport.where("zhihuId = ?", getIntent().getStringExtra(Constant.Extra.EXTRA_ID))
                .find(StoryDetailResult.class);
        if (list != null && list.size() > 0) {
            StoryDetailResult result = list.get(0);
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, result.getTitle() + result.getShareUrl());
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
    }
}
