package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.enums.ContextType;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class Context implements Serializable, Serializer {
    private static final long serialVersionUID = 0x600;

    private ContextType type;
    private String uri;
    private String href;
    private transient JSONObject externalURLs;

    public Context(JSONObject json){
        this.type = ContextType.valueOf(json.getString("type").toUpperCase());
        this.uri = json.getString("uri");
        this.href = json.getString("href");
        this.externalURLs = json.getJSONObject("external_urls");
    }

    public ContextType getType() {
        return type;
    }

    public String getUri() {
        return uri;
    }

    public String getHref() {
        return href;
    }

    public JSONObject getExternalURLs() {
        return externalURLs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Context context = (Context) o;

        if (getType() != context.getType()) return false;
        if (getUri() != null ? !getUri().equals(context.getUri()) : context.getUri() != null) return false;
        if (getHref() != null ? !getHref().equals(context.getHref()) : context.getHref() != null) return false;
        return getExternalURLs() != null ? getExternalURLs().equals(context.getExternalURLs()) : context.getExternalURLs() == null;
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getUri() != null ? getUri().hashCode() : 0);
        result = 31 * result + (getHref() != null ? getHref().hashCode() : 0);
        result = 31 * result + (getExternalURLs() != null ? getExternalURLs().hashCode() : 0);
        return result;
    }
}
