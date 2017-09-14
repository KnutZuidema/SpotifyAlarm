package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Artist;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CursorPagingArtist extends CursorPaging implements Serializable, Serializer {
    private static final long serialVerionUID = 0xA05;

    private List<Artist> items;

    public CursorPagingArtist(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        List<Artist> items = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            items.add(new Artist(array.getJSONObject(i)));
        }
        this.items = items;
    }

    public List<Artist> getItems() {
        return items;
    }
}
