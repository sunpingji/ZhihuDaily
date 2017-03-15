package pjsun.zhihudaily.business.manager;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.utils.NetworkUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.litepal.crud.DataSupport;

import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import pjsun.zhihudaily.business.api.API;
import pjsun.zhihudaily.business.bean.StoryDetailResult;
import pjsun.zhihudaily.business.bean.DailyResult;
import pjsun.zhihudaily.utils.ZhihuDateUtils;

/**
 * Created by sunpingji on 2017/3/8.
 */
public class DataManager implements IDataManage {

    private Context context;

    public DataManager(Context context) {
        this.context = context;
    }


    @Override
    public void getDailyResult(String date, final DataCallBack dataCallBack) {
        if (TextUtils.isEmpty(date)) {
            queryNew(dataCallBack);
        } else {
            queryBefore(date, dataCallBack);
        }
    }

    private void queryBefore(String date, final DataCallBack dataCallBack) {
        List<DailyResult> list = DataSupport.where("date = ?", date).find(DailyResult.class);
        if (list != null && list.size() > 0) {
            dataCallBack.onSuccess(list.get(0));
        } else {
            if (NetworkUtils.isConnected()) {
                OkGo.get(API.BEFORE + date).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        DailyResult result = DailyResult.convertToResult(s);
                        dataCallBack.onSuccess(result);
                        saveResult(result);
                        indexResult(result);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        loadLocal(dataCallBack);
                    }
                });
            } else {
                dataCallBack.onError();
            }
        }

    }

    private void queryNew(final DataCallBack dataCallBack) {
        if (NetworkUtils.isConnected()) {
            OkGo.get(API.NEWS).execute(new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    DailyResult result = DailyResult.convertToResult(s);
                    dataCallBack.onSuccess(result);
                    saveResult(result);
                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    loadLocal(dataCallBack);
                }
            });
        } else {
            loadLocal(dataCallBack);
        }
    }

    @Override
    public void getStoryDetailResult(String id, final DataCallBack dataCallBack) {
        if (NetworkUtils.isConnected()) {
            OkGo.get(API.DETAILS + id).execute(new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    StoryDetailResult result = StoryDetailResult.convertToResult(s);


                    dataCallBack.onSuccess(result);
                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    dataCallBack.onError();
                }
            });
        } else {
            dataCallBack.onError();
        }
    }

    private void loadLocal(DataCallBack dataCallBack) {

    }


    private void indexResult(DailyResult result) {

    }

    private void saveResult(DailyResult result) {
        String date = result.getDate();
        List<DailyResult> list = DataSupport.where("date = ?", date).find(DailyResult.class);
        if (list == null || list.size() == 0) {
            result.save();
        } else {
            if (ZhihuDateUtils.isToday(date)) {
                long id = list.get(0).getBaseObjId();
                result.update(id);
            }
        }
    }
}
