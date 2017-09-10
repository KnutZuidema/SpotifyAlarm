package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class Category implements Serializable, Serializer{
    private static final long serialVersionUID = 0x700;

    private String href;
    private Image[] icons;
    private String id;
    private String name;

    public Category(JSONObject json){
        this.href = json.getString("href");
        this.id = json.getString("id");
        this.name = json.getString("name");
        JSONArray array = json.getJSONArray("icons");
        Image[] icons = new Image[array.length()];
        for(int i = 0; i < array.length(); i++){
            icons[i] = new Image((JSONObject) array.get(i));
        }
        this.icons = icons;
    }

    public String getHref() {
        return href;
    }

    public Image[] getIcons() {
        return icons;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
