package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class User extends SpotifyObject{
    private int followers;
    private Image[] images;

    public User(JSONObject json){
        super(json);
        this.followers = json.getJSONObject("followers").getInt("total");
        JSONArray array = json.getJSONArray("images");
        Image[] images = new Image[array.length()];
        for(int i = 0; i < array.length(); i++){
            images[i] = new Image((JSONObject) array.get(i));
        }
        this.images = images;
    }

    public String getName() {
        return name;
    }

    public JSONObject getExternalURLs() {
        return externalURLs;
    }

    public int getFollowers() {
        return followers;
    }

    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public Image[] getImages() {
        return images;
    }

    public String getUri() {
        return uri;
    }
}
