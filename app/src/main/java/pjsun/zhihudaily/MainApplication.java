package pjsun.zhihudaily;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.orhanobut.logger.Logger;

/**
 * Created by sunpingji on 2017/3/7.
 */

public class MainApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.init(this);
        Logger.init();
    }
}
