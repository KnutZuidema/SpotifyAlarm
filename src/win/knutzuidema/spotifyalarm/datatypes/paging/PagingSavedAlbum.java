package win.knutzuidema.spotifyalarm.datatypes.paging;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Album;
import win.knutzuidema.spotifyalarm.datatypes.SavedAlbum;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PagingSavedAlbum extends Paging implements Serializable, Serializer{
    private static final long serialVersionUID = 0x206;

    private List<SavedAlbum> items;
    private List<Album> albums;

    public PagingSavedAlbum(JSONObject json){
        super(json);
        JSONArray array = json.getJSONArray("items");
        List<SavedAlbum> items = new LinkedList<>();
        List<Album> albums = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            items.add(new SavedAlbum(array.getJSONObject(i)));
            albums.add(new Album(array.getJSONObject(i).getJSONObject("album")));
        }
        this.items = items;
    }

    public List<SavedAlbum> getItems() {
        return items;
    }

    public List<Album> getAlbums() {
        return albums;
    }
}
