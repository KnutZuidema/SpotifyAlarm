package win.knutzuidema.spotifyalarm.datatypes;

import win.knutzuidema.spotifyalarm.enums.RepeatState;

public class Player {
    private Device activeDevice;
    private RepeatState reapeatState;
    private boolean isShuffeling;
    private Context context;
    private int timestamp;
    private int progress;
    private boolean isPlaying;
    private Track currentlyPlaying;

    public Device getActiveDevice() {
        return activeDevice;
    }

    public RepeatState getReapeatState() {
        return reapeatState;
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
