package win.knutzuidema.spotifyalarm.enums;

import java.io.Serializable;

public enum DatePrecision implements Serializable{
    YEAR,
    MONTH,
    DAY;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
