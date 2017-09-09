package win.knutzuidema.spotifyalarm.enums;

public enum ContextType {
    ALBUM,
    ARTIST,
    PLAYLIST;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
