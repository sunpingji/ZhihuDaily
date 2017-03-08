package pjsun.zhihudaily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import okhttp3.Call;
import okhttp3.Response;
import pjsun.zhihudaily.R;
import pjsun.zhihudaily.business.api.API;
import pjsun.zhihudaily.ui.fragment.base.BaseFragment;

/**
 * Created by sunpingji on 2017/3/8.
 */

public class MainFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        OkGo.get(API.NEWS).execute(new StringCallback() {
            @Override
            public void onSuccess(String s, Call call, Response response) {

            }

            @Override
            public void onError(Call call, Response response, Exception e) {
                super.onError(call, response, e);
            }

        });
    }
}
