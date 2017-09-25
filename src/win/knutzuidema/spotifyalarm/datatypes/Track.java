package win.knutzuidema.spotifyalarm.datatypes;

import org.apache.http.client.methods.RequestBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.api.API;
import win.knutzuidema.spotifyalarm.api.Authentication;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.Arrays;

public class Track extends SpotifyObject implements Serializable, Serializer {
    private static final long serialVersionUID = 0x104;

    private Album album;
    private Artist[] artists;
    private String[] availableMarkets;
    private int discNumber;
    private int duration;
    private boolean isExplicit;
    private transient JSONObject externalIDs;
    private boolean isPlayable;
    private TrackLink linkedFrom;
    private String previewURL;
    private int trackNumber;
    private int popularity;

    public Track(JSONObject json){
        super(json);
        if(!json.isNull("album")) {
            this.album = new Album(json.getJSONObject("album"));
            JSONArray array = json.getJSONArray("artists");
            Artist[] artists = new Artist[array.length()];
            for (int i = 0; i < array.length(); i++) {
                artists[i] = new Artist((JSONObject) array.get(i));
            }
            this.artists = artists;
        }
        JSONArray array = json.getJSONArray("available_markets");
        String[] availableMarkets = new String[array.length()];
        for(int i = 0; i < array.length(); i++){
            availableMarkets[i] = (String) array.get(i);
        }
        this.availableMarkets = availableMarkets;
        this.discNumber = json.getInt("disc_number");
        this.duration = json.getInt("duration_ms");
        this.isExplicit = json.getBoolean("explicit");
        this.externalIDs = json.isNull("external_ids") ? null : json.getJSONObject("external_ids");
        this.isPlayable = json.isNull("is_playable") || json.getBoolean("is_playable");
        this.linkedFrom = json.isNull("linked_from") ? null : new TrackLink(json.getJSONObject("linked_from"));
        this.previewURL = json.isNull("preview_url") ? null : json.getString("preview_url");
        this.trackNumber = json.getInt("track_number");
        this.popularity = json.isNull("popularity") ? -1 : json.getInt("popularity");
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

    public int getPopularity() {
        return popularity;
    }

    public Track completeObject(){
        return new Track(API.getJSON(RequestBuilder.create("GET").setUri(href).addHeader(Authentication.bearerAuth()).build()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Track track = (Track) o;

        if (!super.equals(track)) return false;
        if (getDiscNumber() != track.getDiscNumber()) return false;
        if (getDuration() != track.getDuration()) return false;
        if (isExplicit() != track.isExplicit()) return false;
        if (isPlayable() != track.isPlayable()) return false;
        if (getTrackNumber() != track.getTrackNumber()) return false;
        if (getAlbum() != null ? !getAlbum().equals(track.getAlbum()) : track.getAlbum() != null) return false;
        if (!Arrays.equals(getArtists(), track.getArtists())) return false;
        if (!Arrays.equals(getAvailableMarkets(), track.getAvailableMarkets())) return false;
        if (getLinkedFrom() != null ? !getLinkedFrom().equals(track.getLinkedFrom()) : track.getLinkedFrom() != null)
            return false;
        return getPreviewURL() != null ? getPreviewURL().equals(track.getPreviewURL()) : track.getPreviewURL() == null;
    }

    @Override
    public int hashCode() {
        int result = getAlbum() != null ? getAlbum().hashCode() : 0;
        result = 31 * result + Arrays.hashCode(getArtists());
        result = 31 * result + Arrays.hashCode(getAvailableMarkets());
        result = 31 * result + getDiscNumber();
        result = 31 * result + getDuration();
        result = 31 * result + (isExplicit() ? 1 : 0);
        result = 31 * result + (getExternalIDs() != null ? getExternalIDs().hashCode() : 0);
        result = 31 * result + (isPlayable() ? 1 : 0);
        result = 31 * result + (getLinkedFrom() != null ? getLinkedFrom().hashCode() : 0);
        result = 31 * result + (getPreviewURL() != null ? getPreviewURL().hashCode() : 0);
        result = 31 * result + getTrackNumber();
        result = 31 * result + getPopularity();
        return result;
    }
}
