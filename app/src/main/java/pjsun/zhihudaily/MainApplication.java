package pjsun.zhihudaily;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.facebook.stetho.Stetho;
import com.lzy.okgo.OkGo;
import com.tencent.bugly.crashreport.CrashReport;

import org.litepal.LitePal;

import static android.os.Build.ID;

/**
 * Created by sunpingji on 2017/3/7.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.init(this);
        Utils.init(this);
        LitePal.initialize(this);
        Stetho.initializeWithDefaults(this);
        CrashReport.initCrashReport(getApplicationContext(), "28fd1d7829 ", false);
    }
}
