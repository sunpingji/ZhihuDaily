package pjsun.zhihudaily.business.bean;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.Response;

/**
 * Created by sunpingji on 2017/3/8.
 */

public class NewsResult extends BaseBean {

    private List<Story> stories;

    private List<TopStroy> top_stories;

    private String date;

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<TopStroy> getTopStroys() {
        return top_stories;
    }

    public void setTopStroys(List<TopStroy> topStroys) {
        this.top_stories = topStroys;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static NewsResult convertToResult(String s) {
        NewsResult result = new NewsResult();
        try {
            Gson gson = new Gson();
            result = gson.fromJson(s, NewsResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
