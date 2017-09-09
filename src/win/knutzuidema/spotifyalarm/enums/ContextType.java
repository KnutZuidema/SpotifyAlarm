package win.knutzuidema.spotifyalarm.enums;

import java.io.Serializable;

public enum ContextType implements Serializable{
    ALBUM,
    ARTIST,
    PLAYLIST;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
