package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.List;

public abstract class CursorPaging implements Serializable, Serializer{
    private static final long serialVersionUID = 0xA00;

    private String href;
    private int limit;
    private String next;
    private Cursor cursors;
    private int total;

    public CursorPaging(JSONObject json){
        this.href = json.isNull("href") ? null : json.getString("href");
        this.limit = json.isNull("limit") ? -1 : json.getInt("limit");
        this.next = json.isNull("next") ? null : json.getString("next");
        this.cursors = json.isNull("cursors") ? null : new Cursor(json.getJSONObject("cursors"));
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

    public Cursor getCursors() {
        return cursors;
    }

    public int getTotal() {
        return total;
    }
}
