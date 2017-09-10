package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PagingAlbum extends Paging implements Serializable, Serializer {
    private static final long serialVersionUID = 0x201;

    private List<Album> items;

    public PagingAlbum(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        List<Album> items = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            items.add(new Album(array.getJSONObject(i)));
        }
        this.items = items;
    }

    public List<Album> getItems() {
        return items;
    }
}
