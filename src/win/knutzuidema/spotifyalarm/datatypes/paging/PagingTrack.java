package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Track;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PagingTrack extends Paging implements Serializable, Serializer {
    private static final long serialVersionUID = 0x203;

    private List<Track> items;

    public PagingTrack(JSONObject json){
        super(json);
        if(!json.isNull("items")) {
            JSONArray array = json.getJSONArray("items");
            List<Track> items = new LinkedList<>();
            for (int i = 0; i < array.length(); i++) {
                items.add(new Track(array.getJSONObject(i)));
            }
            this.items = items;
        }
    }

    public List<Track> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PagingTrack that = (PagingTrack) o;

        return getItems() != null ? getItems().equals(that.getItems()) : that.getItems() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getItems() != null ? getItems().hashCode() : 0);
        return result;
    }
}
