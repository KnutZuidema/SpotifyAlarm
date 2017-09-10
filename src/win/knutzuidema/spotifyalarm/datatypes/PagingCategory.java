package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PagingCategory extends Paging implements Serializable, Serializer{
    private static final long serialVersionUID = 0x205;

    private List<Category> items;

    public PagingCategory(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        List<Category> items = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            items.add(new Category(array.getJSONObject(i)));
        }
        this.items = items;
    }

    public List<Category> getItems() {
        return items;
    }
}
