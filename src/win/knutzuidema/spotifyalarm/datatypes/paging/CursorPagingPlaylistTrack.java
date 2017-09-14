package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.PlaylistTrack;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CursorPagingPlaylistTrack extends CursorPaging implements Serializable, Serializer {
    private static final long serialVerionUID = 0xA02;

    private List<PlaylistTrack> items;

    public CursorPagingPlaylistTrack(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        List<PlaylistTrack> items = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            items.add(new PlaylistTrack(array.getJSONObject(i)));
        }
        this.items = items;
    }

    public List<PlaylistTrack> getItems() {
        return items;
    }
}
