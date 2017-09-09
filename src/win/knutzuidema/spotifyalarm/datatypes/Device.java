package win.knutzuidema.spotifyalarm.datatypes;

public class Device {
    private String id;
    private String name;
    private String type;
    private int volume;
    private boolean isActive;
    private boolean isRestricted;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getVolume() {
        return volume;
    }

    public boolean isActive() {
        return isActive;
    }

    public boolean isRestricted() {
        return isRestricted;
    }
}
