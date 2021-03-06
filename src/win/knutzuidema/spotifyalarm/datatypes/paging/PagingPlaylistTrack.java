package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.PlaylistTrack;
import win.knutzuidema.spotifyalarm.datatypes.Track;

import java.util.LinkedList;
import java.util.List;

public class PagingPlaylistTrack extends Paging{
    private List<PlaylistTrack> items;

    public PagingPlaylistTrack(JSONObject json){
        super(json);
        if(!json.isNull("items")) {
            JSONArray array = json.getJSONArray("items");
            List<PlaylistTrack> items = new LinkedList<>();
            for (int i = 0; i < array.length(); i++) {
                items.add(new PlaylistTrack(array.getJSONObject(i)));
            }
            this.items = items;
        }
    }

    public List<PlaylistTrack> getItems() {
        return items;
    }

    public List<Track> getTracks(){
        List<Track> tracks = new LinkedList<>();
        for(PlaylistTrack ptrack : getItems()){
            tracks.add(ptrack.getTrack());
        }
        return tracks;
    }
}
