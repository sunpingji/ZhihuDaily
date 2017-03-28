package pjsun.alias.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import pjsun.alias.R;
import pjsun.alias.ui.activity.base.BaseActivity;
import pjsun.alias.ui.fragment.MainFragment;

import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_NO;
import static android.support.v7.app.AppCompatDelegate.MODE_NIGHT_YES;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();

    }

    private void initData() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getFragmentManager().beginTransaction().replace(R.id.fl_container, new MainFragment()).commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                GlobalSearchActivity.start(this);
                return true;
            case R.id.action_night_mode:
                int mode = AppCompatDelegate.getDefaultNightMode() == MODE_NIGHT_YES ? MODE_NIGHT_NO : MODE_NIGHT_YES;
                AppCompatDelegate.setDefaultNightMode(mode);
                recreate();
                return true;
            case R.id.action_about:
                SettingsActivity.start(this);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
