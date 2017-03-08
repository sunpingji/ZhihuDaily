package pjsun.zhihudaily.business.bean;

import okhttp3.Response;

/**
 * Created by sunpingji on 2017/3/8.
 */

public class NewsResult extends BaseBean {


    public static NewsResult convertToResult(Response response) {
        NewsResult result = new NewsResult();
        return result;
    }
}
