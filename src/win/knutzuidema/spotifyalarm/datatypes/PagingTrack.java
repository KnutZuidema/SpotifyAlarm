package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class PagingTrack extends Paging implements Serializable, Serializer {
    private static final long serialVersionUID = 0x203;

    private Track[] items;

    public PagingTrack(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        Track[] items = new Track[array.length()];
        for(int i = 0; i < array.length(); i++){
            items[i] = new Track(array.getJSONObject(i));
        }
        this.items = items;
    }

    public Track[] getItems() {
        return items;
    }
}
