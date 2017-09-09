package win.knutzuidema.spotifyalarm.datatypes;

public class Paging {
    private String href;
    private SpotifyObject[] items;
    private int limit;
    private String next;
    private String previous;
    private int offset;
    private int total;

    public String getHref() {
        return href;
    }

    public SpotifyObject[] getItems() {
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
