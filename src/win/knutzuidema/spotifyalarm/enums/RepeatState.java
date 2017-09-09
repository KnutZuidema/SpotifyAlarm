package win.knutzuidema.spotifyalarm.enums;

import java.io.Serializable;

public enum RepeatState implements Serializable{
    OFF,
    TRACK,
    CONTEXT;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
