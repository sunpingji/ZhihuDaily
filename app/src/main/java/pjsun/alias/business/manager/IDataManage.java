package pjsun.alias.business.manager;

/**
 * Created by sunpingji on 2017/3/8.
 */

public interface IDataManage {

    public void getDailyResult(String date, DataCallBack dataCallBack);

    public void getStoryDetailResult(String id, DataCallBack dataCallBack);
}
