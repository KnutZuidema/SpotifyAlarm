package win.knutzuidema.spotifyalarm.enums;

public enum DatePrecision {
    YEAR,
    MONTH,
    DAY;

    @Override
    public String toString(){
        return this.name().toLowerCase();
    }
}
