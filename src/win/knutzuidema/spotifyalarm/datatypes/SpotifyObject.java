package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;

import java.io.Serializable;

public abstract class SpotifyObject implements Serializable{
    private static final long serialVersionUID = 0x100;

    protected String id;
    protected String uri;
    protected String name;
    protected String href;
    protected transient JSONObject externalURLs;

    protected SpotifyObject(JSONObject json){
        System.out.println(json);
        this.id = json.isNull("id") ? null : json.getString("id");
        this.uri = json.getString("uri");
        this.name = json.isNull("name") ? null : json.getString("name");
        this.href = json.getString("href");
        this.externalURLs = json.getJSONObject("external_urls");
    }

    public String getId() {
        return id;
    }

    public String getUri() {
        return uri;
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

    @Override
    public String toString(){
        return this.name;
    }
}
