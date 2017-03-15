package pjsun.zhihudaily.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pjsun.zhihudaily.Constant;
import pjsun.zhihudaily.R;
import pjsun.zhihudaily.business.bean.StoryDetailResult;
import pjsun.zhihudaily.business.manager.DataCallBack;
import pjsun.zhihudaily.business.manager.DataManager;
import pjsun.zhihudaily.business.search.LuceneManager;
import pjsun.zhihudaily.ui.fragment.base.BaseFragment;
import pjsun.zhihudaily.ui.view.webview.CustomWebviewFragment;
import pjsun.zhihudaily.utils.HtmlUtil;

/**
 * Created by sunpingji on 2017/3/9.
 */

public class DetailFragment extends BaseFragment {

    private String id;
    private DataManager dataManager;
    private CustomWebviewFragment webviewFragment;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViews();
        initData();
    }

    private void initViews() {
        webviewFragment = new CustomWebviewFragment();
        getFragmentManager().beginTransaction().replace(R.id.fl_container, webviewFragment).commitAllowingStateLoss();
    }

    private void initData() {
        id = getActivity().getIntent().getStringExtra(Constant.Extra.EXTRA_ID);
        LuceneManager.getInstance().search("瞎扯");
        dataManager = new DataManager(getActivity());
        if (TextUtils.isEmpty(id)) {
            onLoadError();
        } else {
            loadData();
        }
    }

    private void loadData() {
        dataManager.getStoryDetailResult(id, new DataCallBack<StoryDetailResult>() {
            @Override
            public void onSuccess(StoryDetailResult result) {
                onLoadSuccess(result);
            }

            @Override
            public void onError() {

            }
        });
    }

    private void onLoadSuccess(StoryDetailResult result) {
        String htmlData = HtmlUtil.createHtmlData(result.getBody(), result.getCss(), result.getJs());
        webviewFragment.loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    private void onLoadError() {

    }
}
