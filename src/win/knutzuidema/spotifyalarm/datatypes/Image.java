package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class Image implements Serializable, Serializer {
    private static final long serialVersionUID = 0x00;

    private int height;
    private int width;
    private String url;

    public Image(JSONObject json){
        this.height = json.getInt("height");
        this.width = json.getInt("width");
        this.url = json.getString("url");
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (getHeight() != image.getHeight()) return false;
        if (getWidth() != image.getWidth()) return false;
        return getUrl() != null ? getUrl().equals(image.getUrl()) : image.getUrl() == null;
    }

    @Override
    public int hashCode() {
        int result = getHeight();
        result = 31 * result + getWidth();
        result = 31 * result + (getUrl() != null ? getUrl().hashCode() : 0);
        return result;
    }
}
