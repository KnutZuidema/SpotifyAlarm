package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingPlaylistTrack;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;

public class Playlist extends SpotifyObject implements Serializable, Serializer {
    private static final long serialVersionUID = 0x103;

    private boolean isCollaborative;
    private String description;
    private int followers;
    private Image[] images;
    private User owner;
    private boolean isPublic;
    private String snapshotID;
    private PagingPlaylistTrack tracks;

    public Playlist(JSONObject json){
        super(json);
        this.isCollaborative = json.getBoolean("collaborative");
        this.description = json.isNull("description") ? null : json.getString("description");
        this.followers = json.isNull("followers") ? -1 : json.getJSONObject("followers").getInt("total");
        JSONArray array = json.getJSONArray("images");
        Image[] images = new Image[array.length()];
        for(int i = 0; i < array.length(); i++){
            images[i] = new Image((JSONObject) array.get(i));
        }
        this.images = images;
        this.owner = new User(json.getJSONObject("owner"));
        this.isPublic = !json.isNull("public") && json.getBoolean("public");
        this.snapshotID = json.getString("snapshot_id");
        this.tracks = new PagingPlaylistTrack(json.getJSONObject("tracks"));
    }

    public boolean isCollaborative() {
        return isCollaborative;
    }

    public String getDescription() {
        return description;
    }

    public int getFollowers() {
        return followers;
    }

    public Image[] getImages() {
        return images;
    }

    public User getOwner() {
        return owner;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public String getSnapshotID() {
        return snapshotID;
    }

    public PagingPlaylistTrack getTracks() {
        return tracks;
    }
}
