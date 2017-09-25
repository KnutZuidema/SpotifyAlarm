package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class TrackLink implements Serializable, Serializer{
    private static final long serialVersionUID = 0x300;

    private transient JSONObject externalURLs;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrackLink trackLink = (TrackLink) o;

        if (getHref() != null ? !getHref().equals(trackLink.getHref()) : trackLink.getHref() != null) return false;
        if (getId() != null ? !getId().equals(trackLink.getId()) : trackLink.getId() != null) return false;
        return getUri() != null ? getUri().equals(trackLink.getUri()) : trackLink.getUri() == null;
    }

    @Override
    public int hashCode() {
        int result = getExternalURLs() != null ? getExternalURLs().hashCode() : 0;
        result = 31 * result + (getHref() != null ? getHref().hashCode() : 0);
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getUri() != null ? getUri().hashCode() : 0);
        return result;
    }
}
