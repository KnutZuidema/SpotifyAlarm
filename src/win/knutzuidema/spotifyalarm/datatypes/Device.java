package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class Device implements Serializable, Serializer {
    private static final long serialVersionUID = 0x500;

    private String id;
    private String name;
    private String type;
    private int volume;
    private boolean isActive;
    private boolean isRestricted;

    public Device(JSONObject json){
        this.id = json.isNull("id") ? null : json.getString("id");
        this.name = json.getString("name");
        this.type = json.getString("type");
        this.volume = json.isNull("volume_percent") ? -1 : json.getInt("volume_percent");
        this.isActive = json.getBoolean("is_active");
        this.isRestricted = json.getBoolean("is_restricted");
    }

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
