package pjsun.zhihudaily.ui.activity;

import android.os.Bundle;

import pjsun.zhihudaily.R;
import pjsun.zhihudaily.ui.activity.base.BaseActivity;
import pjsun.zhihudaily.ui.fragment.MainFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
    }

    private void initData() {
        getFragmentManager().beginTransaction().replace(R.id.fl_container, new MainFragment()).commitAllowingStateLoss();
    }
}
