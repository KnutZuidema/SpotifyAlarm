package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;

public abstract class SpotifyObject {
    protected String id;
    protected String uri;
    protected int popularity;
    protected String name;
    protected String href;
    protected JSONObject externalURLs;

    protected SpotifyObject(JSONObject json){
        this.id = json.getString("id");
        this.uri = json.getString("uri");
        this.popularity = json.getInt("popularity");
        this.name = json.getString("name");
        this.href = json.getString("href");
        this.externalURLs = json.getJSONObject("external_urls");
    }

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
