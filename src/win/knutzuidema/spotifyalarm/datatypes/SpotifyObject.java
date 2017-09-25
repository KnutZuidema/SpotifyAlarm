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
        this.id = json.isNull("id") ? null : json.getString("id");
        this.uri = json.isNull("uri") ? null : json.getString("uri");
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SpotifyObject that = (SpotifyObject) o;

        if (getId() != null ? !getId().equals(that.getId()) : that.getId() != null) return false;
        if (getUri() != null ? !getUri().equals(that.getUri()) : that.getUri() != null) return false;
        if (getName() != null ? !getName().equals(that.getName()) : that.getName() != null) return false;
        if (getHref() != null ? !getHref().equals(that.getHref()) : that.getHref() != null) return false;
        return getExternalURLs() != null ? getExternalURLs().equals(that.getExternalURLs()) : that.getExternalURLs() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUri() != null ? getUri().hashCode() : 0);
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getHref() != null ? getHref().hashCode() : 0);
        result = 31 * result + (getExternalURLs() != null ? getExternalURLs().hashCode() : 0);
        return result;
    }
}
