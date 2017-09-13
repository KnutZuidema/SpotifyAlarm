package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Category;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class CursorPagingCategory extends CursorPaging implements Serializable, Serializer {
    private static final long serialVerionUID = 0xA01;

    private List<Category> items;

    public CursorPagingCategory(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        List<Category> items = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            items.add(new Category(array.getJSONObject(i)));
        }
        this.items = items;
    }
}
