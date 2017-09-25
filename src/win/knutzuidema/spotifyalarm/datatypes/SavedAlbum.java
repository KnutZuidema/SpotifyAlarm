package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class SavedAlbum implements Serializable, Serializer{
    private static final long serialVersionUID = 0x1000;

    private String addedAt;
    private Album album;

    public SavedAlbum(JSONObject json){
        this.addedAt = json.getString("added_at");
        this.album = new Album(json.getJSONObject("album"));
    }

    public String getAddedAt() {
        return addedAt;
    }

    public Album getAlbum() {
        return album;
    }
}
