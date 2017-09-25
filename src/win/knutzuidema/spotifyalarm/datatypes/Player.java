package win.knutzuidema.spotifyalarm.datatypes;

import javafx.beans.property.*;
import javafx.beans.value.ObservableStringValue;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.ObservableValueBase;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.api.PlayerAPI;
import win.knutzuidema.spotifyalarm.enums.RepeatState;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Player {
    private Device activeDevice;
    private RepeatState repeatState;
    private boolean isShuffeling;
    private Context context;
    private int timestamp;
    private int progress;
    private boolean isPlaying;
    private ObjectProperty<Track> currentlyPlaying;
    private Thread updateThread;
    private int refreshRate = 1000;
    private ObjectProperty<LocalDateTime> lastUpdated;

    public Player(JSONObject json){
        this.activeDevice = json.isNull("device") ? null : new Device(json.getJSONObject("device"));
        this.repeatState = RepeatState.valueOf(json.getString("repeat_state").toUpperCase());
        this.isShuffeling = json.getBoolean("shuffle_state");
        this.context = json.isNull("context") ? null : new Context(json.getJSONObject("context"));
        this.timestamp = json.getInt("timestamp");
        this.progress = json.isNull("progress_ms") ? -1 : json.getInt("progress_ms");
        this.isPlaying = json.getBoolean("is_playing");
        this.currentlyPlaying = json.isNull("item") ? null : new SimpleObjectProperty<>(new Track(json.getJSONObject("item")));
        Runnable task = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                Player update = new PlayerAPI().getPlayer();
                this.activeDevice = update.getActiveDevice();
                this.repeatState = update.getRepeatState();
                this.isShuffeling = update.isShuffeling();
                this.context = update.getContext();
                this.timestamp = update.getTimestamp();
                this.progress = update.getProgress();
                this.isPlaying = update.isPlaying();
                this.currentlyPlaying.setValue(update.getCurrentlyPlaying());
                this.lastUpdated.setValue(LocalDateTime.now());
                try {
                    Thread.sleep(this.refreshRate);
                } catch (Exception ignored){}
            }
        };
        this.updateThread = new Thread(task);
        updateThread.setDaemon(true);
        this.lastUpdated = new SimpleObjectProperty<>(LocalDateTime.now());
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
        return currentlyPlaying.get();
    }

    public ObjectProperty<Track> currentlyPlayingProperty(){
        return currentlyPlaying;
    }

    public int getRefreshRate() {
        return refreshRate;
    }

    public void setRefreshRate(int refreshRate){
        this.refreshRate = refreshRate;
    }

    public ObjectProperty<LocalDateTime> lastUpdatedProperty() {
        return lastUpdated;
    }

    public void keepSynchronized(boolean doUpdate){
        if(!doUpdate){
            if(updateThread.isAlive()) updateThread.interrupt();
        }else{
            if(updateThread.isAlive()){
                updateThread.interrupt();
            }
            updateThread.start();
        }
    }
}
