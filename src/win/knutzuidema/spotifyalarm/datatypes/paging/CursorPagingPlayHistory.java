package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Album;
import win.knutzuidema.spotifyalarm.datatypes.PlayHistory;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CursorPagingPlayHistory extends CursorPaging implements Serializable, Serializer{
    private static final long serialVersionUID = 0xA07;

    private List<PlayHistory> items;

    public CursorPagingPlayHistory(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        List<PlayHistory> items = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            items.add(new PlayHistory(array.getJSONObject(i)));
        }
        this.items = items;
    }

    public List<PlayHistory> getItems() {
        return items;
    }
}
