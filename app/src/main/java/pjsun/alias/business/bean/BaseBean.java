package pjsun.alias.business.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

/**
 * Created by sunpingji on 2017/3/7.
 */

public class BaseBean extends DataSupport implements Serializable {

    public long getBaseObjId(){
        return super.getBaseObjId();
    }
}
