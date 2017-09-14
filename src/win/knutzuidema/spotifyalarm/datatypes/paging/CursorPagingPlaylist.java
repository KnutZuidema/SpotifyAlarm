package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Playlist;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CursorPagingPlaylist extends CursorPaging implements Serializable, Serializer {
    private static final long serialVerionUID = 0xA03;

    private List<Playlist> items;

    public CursorPagingPlaylist(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        List<Playlist> items = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            items.add(new Playlist(array.getJSONObject(i)));
        }
        this.items = items;
    }

    public List<Playlist> getItems() {
        return items;
    }
}
