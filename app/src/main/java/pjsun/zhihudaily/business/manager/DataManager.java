package pjsun.zhihudaily.business.manager;

import android.content.Context;
import android.net.Network;

import com.blankj.utilcode.utils.NetworkUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;
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
                    dataCallBack.onSuccess(NewsResult.convertToResult(response));
                }

                @Override
                public void onError(Call call, Response response, Exception e) {
                    dataCallBack.onError();
                }
            });
        }
    }
}
