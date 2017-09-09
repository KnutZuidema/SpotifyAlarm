package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class PagingArtist extends Paging{
    private Artist[] items;

    public PagingArtist(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        Artist[] items = new Artist[array.length()];
        for(int i = 0; i < array.length(); i++){
            items[i] = new Artist(array.getJSONObject(i));
        }
        this.items = items;
    }

    public Artist[] getItems() {
        return items;
    }
}
