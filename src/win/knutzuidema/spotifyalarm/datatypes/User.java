package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class User extends SpotifyObject implements Serializable, Serializer {
    private static final long serialVersionUID = 0x105;

    private int followers;
    private Image[] images;

    public User(JSONObject json) {
        super(json);
        this.followers = json.isNull("followers") ? -1 : json.getJSONObject("followers").getInt("total");
        if(!json.isNull("images")) {
            JSONArray array = json.getJSONArray("images");
            Image[] images = new Image[array.length()];
            for (int i = 0; i < array.length(); i++) {
                images[i] = new Image((JSONObject) array.get(i));
            }
            this.images = images;
        }
    }

    public int getFollowers() {
        return followers;
    }

    public Image[] getImages() {
        return images;
    }
}
