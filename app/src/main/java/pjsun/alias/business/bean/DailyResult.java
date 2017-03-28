package pjsun.alias.business.bean;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sunpingji on 2017/3/8.
 */

public class DailyResult extends BaseBean {
    @SerializedName("stories")
    @Expose
    private List<Story> stories;
    @SerializedName("top_stories")
    @Expose
    private List<TopStory> topStories;
    @SerializedName("date")
    @Expose
    private String date;

    private String oriJson;


    public List<Story> getStories() {
        return stories;
    }

    public void setStories(List<Story> stories) {
        this.stories = stories;
    }

    public List<TopStory> getTopStories() {
        return topStories;
    }

    public void setTopStories(List<TopStory> topStories) {
        this.topStories = topStories;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOriJson() {
        return oriJson;
    }

    public void setOriJson(String oriJson) {
        this.oriJson = oriJson;
    }

    public static DailyResult convertToResult(String s) {
        DailyResult result = new DailyResult();
        try {
            Gson gson = new Gson();
            result = gson.fromJson(s, DailyResult.class);
            result.setOriJson(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DailyResult result = (DailyResult) o;

        if (stories != null ? !stories.equals(result.stories) : result.stories != null)
            return false;
        if (topStories != null ? !topStories.equals(result.topStories) : result.topStories != null)
            return false;
        return date.equals(result.date);

    }

    @Override
    public int hashCode() {
        int result = stories != null ? stories.hashCode() : 0;
        result = 31 * result + (topStories != null ? topStories.hashCode() : 0);
        result = 31 * result + date.hashCode();
        return result;
    }
}
