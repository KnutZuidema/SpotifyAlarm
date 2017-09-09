package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class PagingAlbum extends Paging{
    private Album[] items;

    public PagingAlbum(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        Album[] items = new Album[array.length()];
        for(int i = 0; i < array.length(); i++){
            items[i] = new Album(array.getJSONObject(i));
        }
        this.items = items;
    }

    public Album[] getItems() {
        return items;
    }
}
