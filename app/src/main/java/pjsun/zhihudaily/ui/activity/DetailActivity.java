package pjsun.zhihudaily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import pjsun.zhihudaily.Constant;
import pjsun.zhihudaily.R;
import pjsun.zhihudaily.business.bean.Story;
import pjsun.zhihudaily.ui.activity.base.BaseActivity;
import pjsun.zhihudaily.ui.fragment.DetailFragment;

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
}
