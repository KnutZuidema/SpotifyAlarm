package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;

public class Paging {
    protected String href;
    protected int limit;
    protected String next;
    protected String previous;
    protected int offset;
    protected int total;

    public Paging(JSONObject json){
        this.href = json.getString("href");
        this.limit = json.getInt("limit");
        this.next = json.getString("next");
        this.previous = json.getString("previous");
        this.offset = json.getInt("offset");
        this.total = json.getInt("total");
    }

    public String getHref() {
        return href;
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
