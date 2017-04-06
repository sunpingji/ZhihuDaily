package pjsun.alias;

import android.app.Application;

import com.blankj.utilcode.utils.Utils;
import com.facebook.stetho.Stetho;
import com.lzy.okgo.OkGo;

import org.litepal.LitePal;

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
    }
}
