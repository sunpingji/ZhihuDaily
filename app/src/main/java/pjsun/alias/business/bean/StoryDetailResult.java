package pjsun.alias.business.bean;

/**
 * Created by sunpingji on 2017/3/9.
 */

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoryDetailResult extends BaseBean {

    @SerializedName("body")
    @Expose
    private String body;
    @SerializedName("image_source")
    @Expose
    private String imageSource;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("share_url")
    @Expose
    private String shareUrl;
    @SerializedName("js")
    @Expose
    private List<String> js = null;
    @SerializedName("ga_prefix")
    @Expose
    private String gaPrefix;
    @SerializedName("images")
    @Expose
    private List<String> images = null;
    @SerializedName("type")
    @Expose
    private Integer type;
    @SerializedName("id")
    @Expose
    private String zhihuId;
    @SerializedName("css")
    @Expose
    private List<String> css = null;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImageSource() {
        return imageSource;
    }

    public void setImageSource(String imageSource) {
        this.imageSource = imageSource;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public List<String> getJs() {
        return js;
    }

    public void setJs(List<String> js) {
        this.js = js;
    }

    public String getGaPrefix() {
        return gaPrefix;
    }

    public void setGaPrefix(String gaPrefix) {
        this.gaPrefix = gaPrefix;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getZhihuId() {
        return zhihuId;
    }

    public void setZhihuId(String zhihuId) {
        this.zhihuId = zhihuId;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoryDetailResult result = (StoryDetailResult) o;

        return zhihuId != null ? zhihuId.equals(result.zhihuId) : result.zhihuId == null;

    }

    @Override
    public int hashCode() {
        return zhihuId != null ? zhihuId.hashCode() : 0;
    }

    public static StoryDetailResult convertToResult(String s) {
        StoryDetailResult result = new StoryDetailResult();
        try {
            Gson gson = new Gson();
            result = gson.fromJson(s, StoryDetailResult.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}