package pjsun.alias.business.manager;

/**
 * Created by sunpingji on 2017/3/8.
 */

public interface DataCallBack<T> {

    public void onSuccess(T result);

    public void onError();

}
