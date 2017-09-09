package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;

public class Track extends SpotifyObject{
    private Album album;
    private Artist[] artists;
    private String[] availableMarkets;
    private int discNumber;
    private int duration;
    private boolean isExplicit;
    private JSONObject externalIDs;
    private boolean isPlayable;
    private TrackLink linkedFrom;
    private String previewURL;
    private int trackNumber;

    public Track(JSONObject json){
        super(json);
        this.album = new Album(json.getJSONObject("album"));
        JSONArray array = json.getJSONArray("artists");
        Artist[] artists = new Artist[array.length()];
        for(int i = 0; i < array.length(); i++){
            artists[i] = new Artist((JSONObject) array.get(i));
        }
        this.artists = artists;
        array = json.getJSONArray("available_markets");
        String[] availableMarkets = new String[array.length()];
        for(int i = 0; i < array.length(); i++){
            availableMarkets[i] = (String) array.get(i);
        }
        this.availableMarkets = availableMarkets;
        this.discNumber = json.getInt("disc_number");
        this.duration = json.getInt("duration_ms");
        this.isExplicit = json.getBoolean("explicit");
        this.externalIDs = json.getJSONObject("external_ids");
        this.isPlayable = json.getBoolean("is_playable");
        this.linkedFrom = new TrackLink(json.getJSONObject("linked_from"));
        this.previewURL = json.getString("preview_url");
        this.trackNumber = json.getInt("track_number");
    }

    public Album getAlbum() {
        return album;
    }

    public Artist[] getArtists() {
        return artists;
    }

    public String[] getAvailableMarkets() {
        return availableMarkets;
    }

    public int getDiscNumber() {
        return discNumber;
    }

    public int getDuration() {
        return duration;
    }

    public boolean isExplicit() {
        return isExplicit;
    }

    public JSONObject getExternalIDs() {
        return externalIDs;
    }

    public boolean isPlayable() {
        return isPlayable;
    }

    public TrackLink getLinkedFrom() {
        return linkedFrom;
    }

    public String getPreviewURL() {
        return previewURL;
    }

    public int getTrackNumber() {
        return trackNumber;
    }
}
