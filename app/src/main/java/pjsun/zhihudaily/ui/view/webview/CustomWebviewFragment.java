package pjsun.zhihudaily.ui.view.webview;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by sunpingji on 2017/3/8.
 */

public abstract class CustomWebviewFragment extends Fragment implements IWebView {


    protected WebView webView;

    protected View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (getLayoutId() != 0) {
            rootView = (ViewGroup) inflater.inflate(getLayoutId(), container, false);
            return rootView;
        } else {
            return null;
        }
    }

    protected abstract int getLayoutId();

    protected abstract void preInitViews();

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        preInitViews();
        preInitData();
    }

    private void preInitData() {
        setWebSettings(webView.getSettings());
    }


    private void setWebSettings(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH);
        webSettings.setAppCacheMaxSize(1024 * 1024 * 8);
        webSettings.setAllowFileAccess(true);
        webSettings.setAppCacheEnabled(true);
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        webSettings.setSupportZoom(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setGeolocationEnabled(true);
        webSettings.setDatabaseEnabled(true);
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
    }

    @Override
    public void load(String url) {
        webView.loadUrl(url);
    }

    @Override
    public void loadData(String data, String mineType, String encode) {
        webView.loadData(data, mineType, encode);
    }

    @Override
    public void refresh() {

    }
}
