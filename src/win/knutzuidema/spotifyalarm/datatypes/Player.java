package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.enums.RepeatState;

public class Player {
    private Device activeDevice;
    private RepeatState repeatState;
    private boolean isShuffeling;
    private Context context;
    private int timestamp;
    private int progress;
    private boolean isPlaying;
    private Track currentlyPlaying;

    public Player(JSONObject json){
        this.activeDevice = json.isNull("device") ? null : new Device(json.getJSONObject("device"));
        this.repeatState = RepeatState.valueOf(json.getString("repeat_state").toUpperCase());
        this.isShuffeling = json.getBoolean("shuffle_state");
        this.context = json.isNull("context") ? null : new Context(json.getJSONObject("context"));
        this.timestamp = json.getInt("timestamp");
        this.progress = json.isNull("progress_ms") ? -1 : json.getInt("progress_ms");
        this.isPlaying = json.getBoolean("is_playing");
        this.currentlyPlaying = json.isNull("item") ? null : new Track(json.getJSONObject("item"));
    }

    public Device getActiveDevice() {
        return activeDevice;
    }

    public RepeatState getRepeatState() {
        return repeatState;
    }

    public boolean isShuffeling() {
        return isShuffeling;
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

    public Track getCurrentlyPlaying() {
        return currentlyPlaying;
    }
}
