package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.enums.ContextType;

public class Context {
    private ContextType type;
    private String uri;
    private String href;
    private JSONObject externalURLs;
}
