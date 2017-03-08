package pjsun.zhihudaily.dao;

import android.content.Context;

/**
 * Created by sunpingji on 2017/3/8.
 */
public class ZhihuDbHelper {

    private ZhihuDb db;

    public ZhihuDbHelper(Context context) {
        db = new ZhihuDb(context);
    }
}
