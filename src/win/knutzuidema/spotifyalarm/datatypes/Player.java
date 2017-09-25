package win.knutzuidema.spotifyalarm.datatypes;

import javafx.beans.property.*;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.api.PlayerAPI;
import win.knutzuidema.spotifyalarm.enums.RepeatState;

import java.time.LocalDateTime;

public class Player {
    private ObjectProperty<Device> activeDevice;
    private ObjectProperty<RepeatState> repeatState;
    private BooleanProperty isShuffeling;
    private ObjectProperty<Context> context;
    private int timestamp;
    private IntegerProperty progress;
    private BooleanProperty isPlaying;
    private ObjectProperty<Track> currentlyPlaying;
    private Thread updateThread;
    private int refreshRate = 1000;
    private ObjectProperty<LocalDateTime> lastUpdated;

    public Player(JSONObject json){
        this.activeDevice = json.isNull("device") ? new SimpleObjectProperty<>() : new SimpleObjectProperty<>(new Device(json.getJSONObject("device")));
        this.repeatState = new SimpleObjectProperty<>(RepeatState.valueOf(json.getString("repeat_state").toUpperCase()));
        this.isShuffeling = new SimpleBooleanProperty(json.getBoolean("shuffle_state"));
        this.context = json.isNull("context") ? new SimpleObjectProperty<>() : new SimpleObjectProperty<>(new Context(json.getJSONObject("context")));
        this.timestamp = json.getInt("timestamp");
        this.progress = json.isNull("progress_ms") ? new SimpleIntegerProperty() : new SimpleIntegerProperty(json.getInt("progress_ms"));
        this.isPlaying = new SimpleBooleanProperty(json.getBoolean("is_playing"));
        this.currentlyPlaying = json.isNull("item") ? new SimpleObjectProperty<>() : new SimpleObjectProperty<>(new Track(json.getJSONObject("item")));
        Runnable task = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                Player update = PlayerAPI.getPlayer();
                Device updateDevice = update.getActiveDevice();
                Device thisDevice = this.getActiveDevice();
                thisDevice.volumeProperty().setValue(updateDevice.getVolume());
                thisDevice.idProperty().setValue(updateDevice.getId());
                thisDevice.nameProperty().setValue(updateDevice.getName());
                thisDevice.typeProperty().setValue(updateDevice.getType());
                thisDevice.isRestrictedProperty().setValue(updateDevice.isRestricted());
                thisDevice.isActiveProperty().setValue(updateDevice.isActive());
                this.repeatState.setValue(update.getRepeatState());
                this.isShuffeling.setValue(update.isShuffeling());
                this.context.setValue(update.getContext());
                this.timestamp = update.getTimestamp();
                this.progress.setValue(update.getProgress());
                this.isPlaying.setValue(update.isPlaying());
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
        return activeDevice.get();
    }

    public ObjectProperty<Device> activeDeviceProperty() {
        return activeDevice;
    }

    public RepeatState getRepeatState() {
        return repeatStateProperty() == null ? null : repeatState.get();
    }

    public ObjectProperty<RepeatState> repeatStateProperty() {
        return repeatState;
    }

    public boolean isShuffeling() {
        return isShuffeling.get();
    }

    public BooleanProperty isShuffelingProperty() {
        return isShuffeling;
    }

    public Context getContext() {
        return context.get();
    }

    public ObjectProperty<Context> contextProperty() {
        return context;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getProgress() {
        return progress.get();
    }

    public IntegerProperty progressProperty() {
        return progress;
    }

    public boolean isPlaying() {
        return isPlaying.get();
    }

    public BooleanProperty isPlayingProperty() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (getActiveDevice() != player.getActiveDevice()) return false;
        if (getRepeatState() != null ? !getRepeatState().equals(player.getRepeatState()) : player.getRepeatState() != null)
            return false;
        if (isShuffeling() != player.isShuffeling()) return false;
        if (getContext() != null ? !getContext().equals(player.getContext()) : player.getContext() != null)
            return false;
        if (isPlaying() != player.isPlaying()) return false;
        return (getCurrentlyPlaying() != null ? getCurrentlyPlaying().equals(player.getCurrentlyPlaying()) : player.getCurrentlyPlaying() == null);
    }

    @Override
    public int hashCode() {
        int result = getActiveDevice() != null ? getActiveDevice().hashCode() : 0;
        result = 31 * result + (repeatStateProperty() != null ? repeatStateProperty().hashCode() : 0);
        result = 31 * result + (isShuffelingProperty() != null ? isShuffelingProperty().hashCode() : 0);
        result = 31 * result + (contextProperty() != null ? contextProperty().hashCode() : 0);
        result = 31 * result + getTimestamp();
        result = 31 * result + (progressProperty() != null ? progressProperty().hashCode() : 0);
        result = 31 * result + (isPlayingProperty() != null ? isPlayingProperty().hashCode() : 0);
        result = 31 * result + (currentlyPlayingProperty() != null ? currentlyPlayingProperty().hashCode() : 0);
        result = 31 * result + (updateThread != null ? updateThread.hashCode() : 0);
        result = 31 * result + getRefreshRate();
        result = 31 * result + (lastUpdated != null ? lastUpdated.hashCode() : 0);
        return result;
    }
}
