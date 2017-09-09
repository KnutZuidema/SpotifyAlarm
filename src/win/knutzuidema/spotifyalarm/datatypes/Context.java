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
}
