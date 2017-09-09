package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.enums.ContextType;

public class Context {
    private ContextType type;
    private String uri;
    private String href;
    private JSONObject externalURLs;

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
