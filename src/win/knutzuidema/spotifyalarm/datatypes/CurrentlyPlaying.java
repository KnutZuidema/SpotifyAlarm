package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class CurrentlyPlaying implements Serializable, Serializer{
    private Context context;
    private int timestamp;
    private int progress;
    private boolean isPlaying;
    private Track track;

    public CurrentlyPlaying(JSONObject json){
        this.context = json.isNull("context") ? null : new Context(json.getJSONObject("context"));
        this.timestamp = json.isNull("timestamp") ? -1 : json.getInt("timestamp");
        this.progress = json.isNull("progress_ms") ? -1 : json.getInt("progress_ms");
        this.isPlaying = !json.isNull("is_playing") && json.getBoolean("is_playing");
        this.track = json.isNull("item") ? null : new Track(json.getJSONObject("item"));
    }

    public Context getContext() {
        return context;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getProgress() {
        return progress;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public Track getTrack() {
        return track;
    }
}
