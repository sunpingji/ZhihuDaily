package pjsun.zhihudaily.business.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunpingji on 2017/3/15.
 */

public class SearchResult extends BaseBean {

    private List<Story> stories = new ArrayList<>();

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }
}
