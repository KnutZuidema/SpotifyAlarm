package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Album;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CursorPagingAlbum extends CursorPaging implements Serializable, Serializer {
    private static final long serialVerionUID = 0xA01;

    private List<Album> items;

    public CursorPagingAlbum(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        List<Album> items = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            items.add(new Album(array.getJSONObject(i)));
        }
        this.items = items;
    }
}
