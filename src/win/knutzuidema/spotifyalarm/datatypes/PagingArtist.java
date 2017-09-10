package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PagingArtist extends Paging implements Serializable, Serializer {
    private static final long serialVersionUID = 0x202;

    private List<Artist> items;

    public PagingArtist(JSONObject json){
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
