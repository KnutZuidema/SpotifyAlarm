package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.enums.AlbumType;
import win.knutzuidema.spotifyalarm.enums.DatePrecision;

public class Album extends SpotifyObject{
    private AlbumType type;
    private Artist[] artist;
    private String[] availableMarkets;
    private JSONArray copyrights;
    private JSONObject externalIDs;
    private String[] genres;
    private Image[] images;
    private String label;
    private String releaseDate;
    private DatePrecision releaseDatePrecision;
    private Paging tracks;

    public AlbumType getType() {
        return type;
    }

    public Artist[] getArtist() {
        return artist;
    }

    public String[] getAvailableMarkets() {
        return availableMarkets;
    }

    public JSONArray getCopyrights() {
        return copyrights;
    }

    public JSONObject getExternalIDs() {
        return externalIDs;
    }

    public String[] getGenres() {
        return genres;
    }

    public Image[] getImages() {
        return images;
    }

    public String getLabel() {
        return label;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public DatePrecision getReleaseDatePrecision() {
        return releaseDatePrecision;
    }

    public Paging getTracks() {
        return tracks;
    }
}
