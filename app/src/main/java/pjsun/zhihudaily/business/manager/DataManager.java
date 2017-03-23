package pjsun.zhihudaily.business.manager;

import android.content.Context;
import android.text.TextUtils;

import com.blankj.utilcode.utils.NetworkUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import org.litepal.crud.DataSupport;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import pjsun.zhihudaily.business.api.API;
import pjsun.zhihudaily.business.bean.StoryDetailResult;
import pjsun.zhihudaily.business.bean.DailyResult;
import pjsun.zhihudaily.business.search.LuceneManager;
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
        DailyResult localResult = loadLocal(ZhihuDateUtils.getDayBefore(date));
        if (localResult != null) {
            dataCallBack.onSuccess(localResult);
        } else {
            if (NetworkUtils.isConnected()) {
                OkGo.get(API.BEFORE + date).execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        DailyResult result = DailyResult.convertToResult(s);
                        dataCallBack.onSuccess(result);
                        saveResult(result);
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
    }

    private void queryNew(final DataCallBack dataCallBack) {
        DailyResult localResult = loadLocal(ZhihuDateUtils.getToday());
        if (localResult != null) {
            dataCallBack.onSuccess(localResult);
        }
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
                    dataCallBack.onError();
                }
            });
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
                    saveDetailResult(result);
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

    private void saveDetailResult(StoryDetailResult result) {
        List<StoryDetailResult> list = DataSupport.where("zhihuId = ?", result.getZhihuId())
                .find(StoryDetailResult.class);
        if (list == null || list.size() == 0) {
            result.save();
        }
    }

    private DailyResult loadLocal(String date) {
        List<DailyResult> list = DataSupport.where("date = ?", date).find(DailyResult.class);
        if (list != null && list.size() > 0) {
            return DailyResult.convertToResult(list.get(0).getOriJson());
        } else {
            return null;
        }
    }


    private void indexResult(DailyResult result, boolean needUpdate) {
        if (needUpdate) {
            LuceneManager.getInstance().deleteIndex(result.getDate());
        }
        LuceneManager.getInstance().addIndex(result);
    }

    private void saveResult(DailyResult result) {
        String date = result.getDate();
        List<DailyResult> list = DataSupport.where("date = ?", date).find(DailyResult.class);
        if (list == null || list.size() == 0) {
            result.save();
            indexResult(result, false);
        } else {
            if (ZhihuDateUtils.isToday(date) && !list.get(0).equals(result)) {
                long id = list.get(0).getBaseObjId();
                result.update(id);
                indexResult(result, true);
            }
        }
    }
}
