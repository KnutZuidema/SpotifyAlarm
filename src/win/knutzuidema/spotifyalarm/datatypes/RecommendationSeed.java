package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class RecommendationSeed implements Serializable, Serializer{
    private static final long serialVersionUID = 0xD00;

    private String href;
    private String id;
    private String type;
    private int initialPoolSize;
    private int afterFilteringSize;
    private int afterRelinkingSize;

    public RecommendationSeed(JSONObject json){
        this.href = json.isNull("href") ? null : json.getString("href");
        this.id = json.isNull("id") ? null : json.getString("id");
        this.type = json.isNull("type") ? null : json.getString("type");
        this.initialPoolSize = json.isNull("initialPoolSize") ? -1 : json.getInt("initialPoolSize");
        this.afterFilteringSize = json.isNull("afterFilteringSize") ? -1 : json.getInt("afterFilteringSize");
        this.afterRelinkingSize = json.isNull("afterRelinkingSize") ? -1 : json.getInt("afterRelinkingSize");
    }

    public String getHref() {
        return href;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public int getInitialPoolSize() {
        return initialPoolSize;
    }

    public int getAfterFilteringSize() {
        return afterFilteringSize;
    }

    public int getAfterRelinkingSize() {
        return afterRelinkingSize;
    }
}
