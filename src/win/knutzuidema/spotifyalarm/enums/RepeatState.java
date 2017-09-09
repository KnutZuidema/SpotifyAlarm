package win.knutzuidema.spotifyalarm.enums;

public enum RepeatState {
    OFF,
    TRACK,
    CONTEXT;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
