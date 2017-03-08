package pjsun.zhihudaily.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sunpingji on 2017/3/8.
 */

public class ZhihuDb extends SQLiteOpenHelper {

    private static final int version = 1;

    private static final String name = "zhihu.db";

    public ZhihuDb(Context context) {
        super(context, name, null, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists Zhihu("
                + "id integer primary key autoincrement,"
                + "date real,"
                + "content text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
