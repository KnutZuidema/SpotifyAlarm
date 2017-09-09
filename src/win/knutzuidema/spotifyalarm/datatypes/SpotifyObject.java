package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;

public abstract class SpotifyObject {
    protected String id;
    protected String uri;
    protected int popularity;
    protected String name;
    protected String href;
    protected JSONObject externalURLs;

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }

    public int getPopularity() {
        return popularity;
    }

    public String getName() {
        return name;
    }

    public String getHref() {
        return href;
    }

    public JSONObject getExternalURLs() {
        return externalURLs;
    }
}
