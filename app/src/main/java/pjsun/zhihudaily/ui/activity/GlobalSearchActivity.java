package pjsun.zhihudaily.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import pjsun.zhihudaily.R;
import pjsun.zhihudaily.ui.activity.base.BaseActivity;
import pjsun.zhihudaily.ui.fragment.GlobalSearchFragment;

public class GlobalSearchActivity extends BaseActivity {


    public static void start(Context context) {
        Intent intent = new Intent(context, GlobalSearchActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_search);
        initData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    private void initData() {
        getFragmentManager().beginTransaction().replace(R.id.fl_container, new GlobalSearchFragment()).commitAllowingStateLoss();
    }
}
