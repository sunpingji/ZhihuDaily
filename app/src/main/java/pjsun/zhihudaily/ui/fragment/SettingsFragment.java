package pjsun.zhihudaily.ui.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.support.v7.app.AlertDialog;

import com.blankj.utilcode.utils.ToastUtils;

import pjsun.zhihudaily.R;
import pjsun.zhihudaily.business.search.LuceneManager;
import pjsun.zhihudaily.ui.activity.ProfileActivity;

/**
 * Created by sunpingji on 2017/3/22.
 */

public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref_general);
        findPreference("Index").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(R.string.settings_dialog_message);
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (LuceneManager.getInstance().deleteIndex()) {
                            ToastUtils.showShortToast(R.string.index_delete_success);
                        } else {
                            ToastUtils.showShortToast(R.string.index_delete_failed);
                        }
                        dialog.dismiss();
                    }
                });
                builder.show();

                return true;
            }
        });
        findPreference("About").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                ProfileActivity.start(getActivity());
                return true;
            }
        });
    }


}
