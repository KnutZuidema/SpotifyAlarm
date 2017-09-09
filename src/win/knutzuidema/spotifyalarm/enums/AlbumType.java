package win.knutzuidema.spotifyalarm.enums;

public enum AlbumType {
    ALBUM,
    SINGLE,
    APPEARS_ON,
    COMPILATION;

    @Override
    public String toString(){
        return name().toLowerCase();
    }
}
