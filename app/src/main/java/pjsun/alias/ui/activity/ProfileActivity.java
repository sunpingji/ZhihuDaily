package pjsun.alias.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.vansuita.materialabout.builder.AboutBuilder;
import com.vansuita.materialabout.views.AboutView;

import pjsun.alias.R;
import pjsun.alias.ui.activity.base.BaseActivity;

public class ProfileActivity extends BaseActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, ProfileActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AboutView view = AboutBuilder.with(this)
                .setPhoto(R.mipmap.ic_profile)
                .setCover(R.mipmap.profile_cover)
                .setName("SunPingji")
                .setSubTitle("Android Developer")
                .setBrief("Just a programmer")
                .setAppIcon(R.mipmap.ic_launcher)
                .setAppName(R.string.app_name)
                .addGitHubLink("sunpingji")
                .addFacebookLink("sunpingji")
                .addFiveStarsAction()
                .setVersionNameAsAppSubTitle()
                .addShareAction(R.string.app_name)
                .setWrapScrollView(true)
                .setLinksAnimated(true)
                .setShowAsCard(true)
                .build();
        setContentView(view);

    }
}
