package pjsun.alias.ui.activity;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import pjsun.alias.R;
import pjsun.alias.ui.activity.base.BaseActivity;
import pjsun.alias.ui.fragment.SettingsFragment;


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
