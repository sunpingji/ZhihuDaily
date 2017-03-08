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
import pjsun.zhihudaily.business.bean.NewsResult;
import pjsun.zhihudaily.business.manager.DataCallBack;
import pjsun.zhihudaily.business.manager.DataManager;
import pjsun.zhihudaily.ui.fragment.base.BaseFragment;

/**
 * Created by sunpingji on 2017/3/8.
 */

public class MainFragment extends BaseFragment {

    private DataManager dataManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initData();
    }

    private void initViews() {
        getActivity().findViewById(R.id.load).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initData();
            }
        });
    }

    private void initData() {
        dataManager = new DataManager(getActivity());
        dataManager.getNewsResult(new DataCallBack() {
            @Override
            public void onSuccess(NewsResult result) {

            }

            @Override
            public void onError() {

            }
        });
    }
}
