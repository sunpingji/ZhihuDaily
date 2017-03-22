package pjsun.zhihudaily.ui.fragment;

import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;

import pjsun.zhihudaily.R;
import pjsun.zhihudaily.ui.activity.ProfileActivity;

/**
 * Created by sunpingji on 2017/3/22.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        findPreference("About").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ProfileActivity.start(getActivity());
                return true;
            }
        });
    }


}
