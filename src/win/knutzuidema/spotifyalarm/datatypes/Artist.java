package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;

public class Artist extends SpotifyObject{
    private int followers;
    private String[] genres;
    private Image[] images;

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
