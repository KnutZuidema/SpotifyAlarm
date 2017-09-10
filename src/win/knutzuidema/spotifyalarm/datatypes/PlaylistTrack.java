package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class PlaylistTrack implements Serializable, Serializer{
    private static final long serialVersionUID = 0x800;

    private String addedAt;
    private User addedBy;
    private boolean isLocal;
    private Track track;

    public PlaylistTrack(JSONObject json){
        this.addedAt = json.isNull("added_at") ? null : json.getString("added_at");
        this.addedBy = json.isNull("added_by") ? null : new User(json.getJSONObject("added_by"));
        this.isLocal = !json.isNull("is_local") && json.getBoolean("is_local");
        this.track = json.isNull("track") ? null : new Track(json.getJSONObject("track"));
    }

    public String getAddedAt() {
        return addedAt;
    }

    public User getAddedBy() {
        return addedBy;
    }

    public boolean isLocal() {
        return isLocal;
    }

    public Track getTrack() {
        return track;
    }
}
