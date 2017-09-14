package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class PlayHistory implements Serializable, Serializer{
    private static final long serialVersionUID = 0xC00;

    private Track track;
    private String playedAt;
    private Context context;

    public PlayHistory(JSONObject json){
        this.track = new Track(json.getJSONObject("track"));
        this.playedAt = json.getString("timestamp");
        this.context = new Context(json.getJSONObject("context"));
    }

    public Track getTrack() {
        return track;
    }

    public String getPlayedAt() {
        return playedAt;
    }

    public Context getContext() {
        return context;
    }
}
