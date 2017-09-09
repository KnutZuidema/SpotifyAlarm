package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class Playlist extends SpotifyObject{
    private boolean isCollaborative;
    private String description;
    private int followers;
    private Image[] images;
    private User owner;
    private boolean isPublic;
    private String snapshotID;
    private PagingTrack tracks;

    public Playlist(JSONObject json){
        super(json);
        this.isCollaborative = json.getBoolean("collaborative");
        this.description = json.getString("description");
        this.followers = json.getJSONObject("followers").getInt("total");
        JSONArray array = json.getJSONArray("images");
        Image[] images = new Image[array.length()];
        for(int i = 0; i < array.length(); i++){
            images[i] = new Image((JSONObject) array.get(i));
        }
        this.images = images;
        this.owner = new User(json.getJSONObject("owner"));
        this.isPublic = json.getBoolean("public");
        this.snapshotID = json.getString("snapshot_id");
        this.tracks = new PagingTrack(json.getJSONObject("tracks"));
    }

    public boolean isCollaborative() {
        return isCollaborative;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public JSONObject getExternalURLs() {
        return externalURLs;
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

    public PagingTrack getTracks() {
        return tracks;
    }

    @Override
    public String getUri() {
        return uri;
    }
}