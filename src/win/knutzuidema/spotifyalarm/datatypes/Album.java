package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.enums.AlbumType;
import win.knutzuidema.spotifyalarm.enums.DatePrecision;

public class Album extends SpotifyObject{
    private AlbumType type;
    private Artist[] artists;
    private String[] availableMarkets;
    private JSONArray copyrights;
    private JSONObject externalIDs;
    private String[] genres;
    private Image[] images;
    private String label;
    private String releaseDate;
    private DatePrecision releaseDatePrecision;
    private PagingTrack tracks;
    private int popularity;

    public Album(JSONObject json){
        super(json);
        this.type = AlbumType.valueOf(json.getString("album_type").toUpperCase());
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
        this.copyrights = json.getJSONArray("copyrights");
        this.externalIDs = json.getJSONObject("external_ids");
        array = json.getJSONArray("genres");
        String[] genres = new String[array.length()];
        for(int i = 0; i < array.length(); i++){
            genres[i] = (String) array.get(i);
        }
        this.genres = genres;
        array = json.getJSONArray("images");
        Image[] images = new Image[array.length()];
        for(int i = 0; i < array.length(); i++){
            images[i] = new Image((JSONObject) array.get(i));
        }
        this.images = images;
        this.label = json.getString("label");
        this.releaseDate = json.getString("release_date");
        this.releaseDatePrecision = DatePrecision.valueOf(json.getString("release_date_precision".toUpperCase()));
        this.tracks = new PagingTrack(json.getJSONObject("tracks"));
        this.popularity = json.getInt("popularity");
    }

    public AlbumType getType() {
        return type;
    }

    public Artist[] getArtists() {
        return artists;
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

    public PagingTrack getTracks() {
        return tracks;
    }

    public int getPopularity() {
        return popularity;
    }
}
