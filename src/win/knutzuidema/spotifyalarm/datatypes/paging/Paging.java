package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONObject;

import java.io.Serializable;

public abstract class Paging implements Serializable{
    private static final long serialVersionUID = 0x200;

    protected String href;
    protected int limit;
    protected String next;
    protected String previous;
    protected int offset;
    protected int total;

    public Paging(JSONObject json){
        this.href = json.getString("href");
        this.limit = json.isNull("limit") ? 20 : json.getInt("limit");
        this.next = json.isNull("next") ? null : json.getString("next");
        this.previous = json.isNull("previous") ? null : json.getString("previous");
        this.offset = json.isNull("offset") ? 0 : json.getInt("offset");
        this.total = json.isNull("total") ? -1 : json.getInt("total");
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
