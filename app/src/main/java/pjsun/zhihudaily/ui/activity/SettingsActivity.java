package pjsun.zhihudaily.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import pjsun.zhihudaily.R;
import pjsun.zhihudaily.ui.activity.base.BaseActivity;
import pjsun.zhihudaily.ui.fragment.SettingsFragment;


public class SettingsActivity extends BaseActivity {


    public static void start(Context context){
        Intent intent = new Intent(context,SettingsActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        initData();

    }

    private void initData() {
        getFragmentManager().beginTransaction().replace(R.id.fl_container, new SettingsFragment()).commitAllowingStateLoss();
    }

}
