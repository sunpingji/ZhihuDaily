package pjsun.alias.business.bean;

/**
 * Created by sunpingji on 2017/3/7.
 */

public class TopStory extends BaseBean {
    private String image;
    private int type;
    private String id;
    private int ga_prefix;
    private String title;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(int ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TopStory topStory = (TopStory) o;

        return id.equals(topStory.id);

    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
