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
}
