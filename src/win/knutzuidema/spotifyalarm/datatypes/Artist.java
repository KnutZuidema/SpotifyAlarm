package win.knutzuidema.spotifyalarm.datatypes;

import org.apache.http.client.methods.RequestBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.api.API;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class Artist extends SpotifyObject implements Serializable, Serializer {
    private static final long serialVersionUID = 0x102;

    private int followers;
    private String[] genres;
    private Image[] images;
    private int popularity;

    public Artist(JSONObject json){
        super(json);
        this.followers = json.isNull("followers") ? -1 : json.getJSONObject("followers").getInt("total");
        if(!json.isNull("genres")) {
            JSONArray array = json.getJSONArray("genres");
            String[] genres = new String[array.length()];
            for (int i = 0; i < array.length(); i++) {
                genres[i] = (String) array.get(i);
            }
            this.genres = genres;
        }
        if(!json.isNull("images")) {
            JSONArray array = json.getJSONArray("images");
            Image[] images = new Image[array.length()];
            for (int i = 0; i < array.length(); i++) {
                images[i] = new Image((JSONObject) array.get(i));
            }
            this.images = images;
        }
        this.popularity = json.isNull("popularity") ? -1 : json.getInt("popularity");
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

    public int getPopularity() {
        return popularity;
    }

    public Artist completeObject(){
        return new Artist(API.getJSON(RequestBuilder.create("GET").setUri(href).build()));
    }
}
