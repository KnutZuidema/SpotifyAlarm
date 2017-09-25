package win.knutzuidema.spotifyalarm.datatypes;

import org.apache.http.client.methods.RequestBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.api.API;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingTrack;
import win.knutzuidema.spotifyalarm.enums.AlbumType;
import win.knutzuidema.spotifyalarm.enums.DatePrecision;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.lang.reflect.Field;

public class Album extends SpotifyObject implements Serializable, Serializer{
    private static final long serialVersionUID = 0x101;

    private AlbumType type;
    private Artist[] artists;
    private String[] availableMarkets;
    private transient JSONArray copyrights;
    private transient JSONObject externalIDs;
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
        this.copyrights = json.isNull("copyrights") ? null : json.getJSONArray("copyrights");
        this.externalIDs = json.isNull("external_IDs") ? null : json.getJSONObject("external_ids");
        if(!json.isNull("genres")) {
            array = json.getJSONArray("genres");
            String[] genres = new String[array.length()];
            for (int i = 0; i < array.length(); i++) {
                genres[i] = (String) array.get(i);
            }
            this.genres = genres;
        }
        array = json.getJSONArray("images");
        Image[] images = new Image[array.length()];
        for(int i = 0; i < array.length(); i++){
            images[i] = new Image((JSONObject) array.get(i));
        }
        this.images = images;
        this.label = json.isNull("label") ? null : json.getString("label");
        this.releaseDate = json.isNull("release_date") ? null : json.getString("release_date");
        this.releaseDatePrecision = json.isNull("release_date_precision") ? null : DatePrecision.valueOf(json.getString("release_date_precision".toUpperCase()));
        this.tracks = json.isNull("tracks") ? null : new PagingTrack(json.getJSONObject("tracks"));
        this.popularity = json.isNull("popularity") ? -1 : json.getInt("popularity");
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

    public Album completeObject(){
        return new Album(API.getJSON(RequestBuilder.create("GET").setUri(href).build()));
    }
}
