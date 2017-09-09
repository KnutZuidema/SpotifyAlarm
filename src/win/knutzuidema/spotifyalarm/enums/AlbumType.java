package win.knutzuidema.spotifyalarm.enums;

import java.io.Serializable;

public enum AlbumType implements Serializable{
    ALBUM,
    SINGLE,
    APPEARS_ON,
    COMPILATION;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
