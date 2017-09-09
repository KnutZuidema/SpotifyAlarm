package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;

public class TrackLink {
    private JSONObject externalURLs;
    private String href;
    private String id;
    private String uri;

    public TrackLink(JSONObject json){
        this.externalURLs = json.getJSONObject("external_urls");
        this.href = json.getString("href");
        this.id = json.getString("id");
        this.uri = json.getString("uri");
    }

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
