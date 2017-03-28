package pjsun.alias.ui.fragment;

import android.os.Bundle;
import android.webkit.WebView;

import pjsun.alias.Constant;
import pjsun.alias.R;
import pjsun.alias.business.bean.StoryDetailResult;
import pjsun.alias.ui.view.webview.CustomWebviewFragment;
import pjsun.alias.utils.HtmlUtil;

/**
 * Created by sunpingji on 2017/3/9.
 */

public class DetailFragment extends CustomWebviewFragment {

    private StoryDetailResult result;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_detail;
    }

    @Override
    protected void preInitViews() {
        webView = (WebView) rootView.findViewById(R.id.webview);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }


    private void initData() {
        result = (StoryDetailResult) getArguments().getSerializable(Constant.Extra.EXTRA_DETAIL_RESULT);
        if (result != null) {
            onLoadSuccess(result);
        } else {
            onLoadError();
        }
    }


    private void onLoadSuccess(StoryDetailResult result) {
        String htmlData = HtmlUtil.createHtmlData(result.getBody(), result.getCss(), result.getJs());
        loadData(htmlData, HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }

    private void onLoadError() {

    }
}
