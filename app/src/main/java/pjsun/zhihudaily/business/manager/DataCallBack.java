package pjsun.zhihudaily.business.manager;

import pjsun.zhihudaily.business.bean.NewsResult;

/**
 * Created by sunpingji on 2017/3/8.
 */

public interface DataCallBack {

    public void onSuccess(NewsResult result);

    public void onError();

}
