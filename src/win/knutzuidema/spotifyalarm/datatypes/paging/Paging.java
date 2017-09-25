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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Paging paging = (Paging) o;

        if (getLimit() != paging.getLimit()) return false;
        if (getOffset() != paging.getOffset()) return false;
        if (getTotal() != paging.getTotal()) return false;
        if (getHref() != null ? !getHref().equals(paging.getHref()) : paging.getHref() != null) return false;
        if (getNext() != null ? !getNext().equals(paging.getNext()) : paging.getNext() != null) return false;
        return getPrevious() != null ? getPrevious().equals(paging.getPrevious()) : paging.getPrevious() == null;
    }

    @Override
    public int hashCode() {
        int result = getHref() != null ? getHref().hashCode() : 0;
        result = 31 * result + getLimit();
        result = 31 * result + (getNext() != null ? getNext().hashCode() : 0);
        result = 31 * result + (getPrevious() != null ? getPrevious().hashCode() : 0);
        result = 31 * result + getOffset();
        result = 31 * result + getTotal();
        return result;
    }
}
