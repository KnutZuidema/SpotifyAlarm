package win.knutzuidema.spotifyalarm.enums;

public enum TimeFrame {
    LONG_TERM,
    MEDIUM_TERM,
    SHORT_TERM;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
