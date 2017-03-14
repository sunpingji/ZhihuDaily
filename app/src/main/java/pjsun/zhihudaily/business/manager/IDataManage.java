package pjsun.zhihudaily.business.manager;

/**
 * Created by sunpingji on 2017/3/8.
 */

public interface IDataManage {

    public void getNewsResult(String date, DataCallBack dataCallBack);

    public void getNewsDetailResult(String id, DataCallBack dataCallBack);
}
