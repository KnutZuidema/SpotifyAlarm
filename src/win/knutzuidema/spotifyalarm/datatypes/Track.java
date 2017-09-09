package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONObject;

public class Track extends SpotifyObject{
    private Album album;
    private Artist artist;
    private String[] availableMarkets;
    private int discNumber;
    private int duration;
    private boolean isExplicit;
    private JSONObject externalIDs;
    private boolean isPlayable;
    private TrackLink linkedFrom;
    private String previewURL;
    private int trackNumber;

    public Album getAlbum() {
        return album;
    }

    public Artist getArtist() {
        return artist;
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
