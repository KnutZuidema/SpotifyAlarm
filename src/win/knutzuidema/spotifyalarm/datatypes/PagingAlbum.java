package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class PagingAlbum {
    private String href;
    private Album[] items;
    private int limit;
    private String next;
    private String previous;
    private int offset;
    private int total;

    public PagingAlbum(JSONObject json){
        this.href = json.getString("href");
        JSONArray array = json.getJSONArray("items");
        Album[] items = new Album[array.length()];
        for(int i = 0; i < array.length(); i++){
            items[i] = new Album(array.getJSONObject(i));
        }
        this.items = items;
        this.limit = json.getInt("limit");
        this.next = json.getString("next");
        this.previous = json.getString("previous");
        this.offset = json.getInt("offset");
        this.total = json.getInt("total");
    }

    public String getHref() {
        return href;
    }

    public Album[] getItems() {
        return items;
    }

    public int getLimit() {
        return limit;
    }

    public String getNext() {
        return next;
    }

    public String getPrevious() {
        return previous;
    }

    public int getOffset() {
        return offset;
    }

    public int getTotal() {
        return total;
    }
}
