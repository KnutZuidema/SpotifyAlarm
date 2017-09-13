package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Track;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CursorPagingTrack extends CursorPaging implements Serializable, Serializer {
    private static final long serialVerionUID = 0xA01;

    private List<Track> items;

    public CursorPagingTrack(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        List<Track> items = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            items.add(new Track(array.getJSONObject(i)));
        }
        this.items = items;
    }
}
