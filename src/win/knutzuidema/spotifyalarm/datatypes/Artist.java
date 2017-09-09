package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class Artist extends SpotifyObject{
    private int followers;
    private String[] genres;
    private Image[] images;

    public Artist(JSONObject json){
        super(json);
        this.followers = json.getInt("followers");
        JSONArray array = json.getJSONArray("genres");
        String[] genres = new String[array.length()];
        for(int i = 0; i < array.length(); i++){
            genres[i] = (String) array.get(i);
        }
        this.genres = genres;
        array = json.getJSONArray("images");
        Image[] images = new Image[array.length()];
        for(int i = 0; i < array.length(); i++){
            images[i] = new Image((JSONObject) array.get(i));
        }
        this.images = images;

    }

    public int getFollowers() {
        return followers;
    }

    public String[] getGenres() {
        return genres;
    }

    public Image[] getImages() {
        return images;
    }
}
