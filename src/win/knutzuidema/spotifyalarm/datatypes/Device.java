package win.knutzuidema.spotifyalarm.datatypes;

import javafx.beans.property.*;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class Device implements Serializable, Serializer {
    private static final long serialVersionUID = 0x500;

    private StringProperty id;
    private StringProperty name;
    private StringProperty type;
    private IntegerProperty volume;
    private BooleanProperty isActive;
    private BooleanProperty isRestricted;

    public Device(JSONObject json){
        this.id = json.isNull("id") ? null : new SimpleStringProperty(json.getString("id"));
        this.name = new SimpleStringProperty(json.getString("name"));
        this.type = new SimpleStringProperty(json.getString("type"));
        this.volume = json.isNull("volume_percent") ? null : new SimpleIntegerProperty(json.getInt("volume_percent"));
        this.isActive = new SimpleBooleanProperty(json.getBoolean("is_active"));
        this.isRestricted = new SimpleBooleanProperty(json.getBoolean("is_restricted"));
    }

    public String getId() {
        return id.get();
    }

    public StringProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public String getType() {
        return type.get();
    }

    public StringProperty typeProperty() {
        return type;
    }

    public int getVolume() {
        return volume.get();
    }

    public IntegerProperty volumeProperty() {
        return volume;
    }

    public boolean isActive() {
        return isActive.get();
    }

    public BooleanProperty isActiveProperty() {
        return isActive;
    }

    public boolean isRestricted() {
        return isRestricted.get();
    }

    public BooleanProperty isRestrictedProperty() {
        return isRestricted;
    }

    @Override
    public int hashCode() {
        int result = idProperty() != null ? idProperty().hashCode() : 0;
        result = 31 * result + (nameProperty() != null ? nameProperty().hashCode() : 0);
        result = 31 * result + (typeProperty() != null ? typeProperty().hashCode() : 0);
        result = 31 * result + (volumeProperty() != null ? volumeProperty().hashCode() : 0);
        result = 31 * result + (isActiveProperty() != null ? isActiveProperty().hashCode() : 0);
        result = 31 * result + (isRestrictedProperty() != null ? isRestrictedProperty().hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Device device = (Device) o;

        if (getId() != null ? !getId().equals(device.getId()) : device.getId() != null) return false;
        if (getName() != null ? !getName().equals(device.getName()) : device.getName() != null) return false;
        if (getType() != null ? !getType().equals(device.getType()) : device.getType() != null) return false;
        if (getVolume() != device.getVolume()) return false;
        if (isActive() != device.isActive()) return false;
        return isRestricted() == device.isRestricted();
    }
}