package pjsun.zhihudaily.business.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunpingji on 2017/3/8.
 */

public class NewsResult extends BaseBean {
    @SerializedName("stories")
    @Expose
    private List<Story> stories;
    @SerializedName("top_stories")
    @Expose
    private List<TopStroy> topStories;
    @SerializedName("date")
    @Expose
    private String date;

    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<TopStroy> getTopStroys() {
        return topStories;
    }

    public void setTopStroys(List<TopStroy> topStroys) {
        this.topStories = topStroys;
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
