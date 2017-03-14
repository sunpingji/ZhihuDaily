package pjsun.zhihudaily.business.manager;

import java.util.List;

/**
 * Created by sunpingji on 2017/3/8.
 */

public interface DataCallBack<T> {

    public void onSuccess(List<T> result);

    public void onError();

}
