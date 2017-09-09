package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;

public class TrackLink {
    private JSONObject externalURLs;
    private String href;
    private String id;
    private String uri;

    public JSONObject getExternalURLs() {
        return externalURLs;
    }

    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
    }
}
