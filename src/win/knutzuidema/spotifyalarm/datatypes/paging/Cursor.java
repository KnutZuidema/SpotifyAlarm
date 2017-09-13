package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class Cursor implements Serializable, Serializer{
    private static final long serialVersionUID = 0xB00;

    private String after;

    public Cursor(JSONObject json){
        this.after = json.isNull("after") ? null : json.getString("after");
    }

    public String getAfter() {
        return after;
    }
}
