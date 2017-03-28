package pjsun.alias.ui.view.webview;

/**
 * Created by sunpingji on 2017/3/9.
 */

public interface IWebView {
    public void load(String url);

    public void loadData(String data,String mineType,String encode);

    public void refresh();


}
