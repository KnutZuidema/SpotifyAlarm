package win.knutzuidema.spotifyalarm.api;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Map;

public class JSONFormEntity extends StringEntity {
    private JSONObject source;

    public JSONFormEntity(String source){
        super(source, ContentType.APPLICATION_JSON);
    }

    public JSONFormEntity(JSONObject source){
        this(source.toString());
    }

    private JSONFormEntity(){
        super("", ContentType.APPLICATION_JSON);
    }

    public static JSONFormEntity create(){
        return new JSONFormEntity();
    }

    public JSONFormEntity addParameter(String name, int value){
        if(this.source == null){
            this.source = new JSONObject();
        }
        this.source.put(name, value);
        return this;
    }

    public JSONFormEntity addParameter(String name, boolean value){
        if(this.source == null){
            this.source = new JSONObject();
        }
        this.source.put(name, value);
        return this;
    }

    public JSONFormEntity addParameter(String name, long value){
        if(this.source == null){
            this.source = new JSONObject();
        }
        this.source.put(name, value);
        return this;
    }

    public JSONFormEntity addParameter(String name, double value){
        if(this.source == null){
            this.source = new JSONObject();
        }
        this.source.put(name, value);
        return this;
    }

    public JSONFormEntity addParameter(String name, float value){
        if(this.source == null){
            this.source = new JSONObject();
        }
        this.source.put(name, value);
        return this;
    }

    public JSONFormEntity addParameter(String name, Collection<?> value){
        if(this.source == null){
            this.source = new JSONObject();
        }
        this.source.put(name, value);
        return this;
    }

    public JSONFormEntity addParameter(String name, Map<?, ?> value){
        if(this.source == null){
            this.source = new JSONObject();
        }
        this.source.put(name, value);
        return this;
    }

    public JSONFormEntity addParameter(String name, Object value){
        if(this.source == null){
            this.source = new JSONObject();
        }
        this.source.put(name, value);
        return this;
    }

    public JSONFormEntity build(){
        return new JSONFormEntity(this.source);
    }
}
