package pjsun.zhihudaily.business.manager;

import android.content.Context;

import com.blankj.utilcode.utils.NetworkUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;
import pjsun.zhihudaily.business.api.API;
import pjsun.zhihudaily.business.bean.NewsResult;

/**
 * Created by sunpingji on 2017/3/8.
 */
public class DataManager implements IDataManage {

    private Context context;

    public DataManager(Context context) {
        this.context = context;
    }


    @Override
    public void getNewsResult(final DataCallBack dataCallBack) {
        if (NetworkUtils.isConnected()) {
            OkGo.get(API.NEWS).execute(new StringCallback() {
                @Override
                public void onSuccess(String s, Call call, Response response) {
                    NewsResult result = NewsResult.convertToResult(s);
                    dataCallBack.onSuccess(result);
                    saveResult(result);
                    indexResult(result);
                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    loadLocal(dataCallBack);
                }
            });
        }else {
            loadLocal(dataCallBack);
        }
    }

    private void loadLocal(DataCallBack dataCallBack) {

    }


    private void indexResult(NewsResult result) {

    }

    private void saveResult(NewsResult result) {

    }
}
