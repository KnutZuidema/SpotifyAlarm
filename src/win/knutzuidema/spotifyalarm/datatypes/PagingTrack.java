package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class PagingTrack {
    private String href;
    private Track[] items;
    private int limit;
    private String next;
    private String previous;
    private int offset;
    private int total;

    public PagingTrack(JSONObject json){
        this.href = json.getString("href");
        JSONArray array = json.getJSONArray("items");
        Track[] items = new Track[array.length()];
        for(int i = 0; i < array.length(); i++){
            items[i] = new Track(array.getJSONObject(i));
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

    public Track[] getItems() {
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
