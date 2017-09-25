package win.knutzuidema.spotifyalarm.datatypes;

import org.apache.http.client.methods.RequestBuilder;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.api.API;
import win.knutzuidema.spotifyalarm.api.Authentication;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingTrack;
import win.knutzuidema.spotifyalarm.enums.AlbumType;
import win.knutzuidema.spotifyalarm.enums.DatePrecision;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.Arrays;

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
        this.releaseDatePrecision = json.isNull("release_date_precision") ? null : DatePrecision.valueOf(json.getString("release_date_precision").toUpperCase());
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
        return new Album(API.getJSON(RequestBuilder.create("GET").setUri(href).addHeader(Authentication.bearerAuth()).build()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (!super.equals(album)) return false;
        if (getPopularity() != album.getPopularity()) return false;
        if (getType() != album.getType()) return false;
        if (!Arrays.equals(getArtists(), album.getArtists())) return false;
        if (!Arrays.equals(getAvailableMarkets(), album.getAvailableMarkets())) return false;
        if (!Arrays.equals(getGenres(), album.getGenres())) return false;
        if (!Arrays.equals(getImages(), album.getImages())) return false;
        if (getLabel() != null ? !getLabel().equals(album.getLabel()) : album.getLabel() != null) return false;
        if (getReleaseDate() != null ? !getReleaseDate().equals(album.getReleaseDate()) : album.getReleaseDate() != null)
            return false;
        if (getReleaseDatePrecision() != album.getReleaseDatePrecision()) return false;
        return getTracks() != null ? getTracks().equals(album.getTracks()) : album.getTracks() == null;
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + Arrays.hashCode(getArtists());
        result = 31 * result + Arrays.hashCode(getAvailableMarkets());
        result = 31 * result + (getCopyrights() != null ? getCopyrights().hashCode() : 0);
        result = 31 * result + (getExternalIDs() != null ? getExternalIDs().hashCode() : 0);
        result = 31 * result + Arrays.hashCode(getGenres());
        result = 31 * result + Arrays.hashCode(getImages());
        result = 31 * result + (getLabel() != null ? getLabel().hashCode() : 0);
        result = 31 * result + (getReleaseDate() != null ? getReleaseDate().hashCode() : 0);
        result = 31 * result + (getReleaseDatePrecision() != null ? getReleaseDatePrecision().hashCode() : 0);
        result = 31 * result + (getTracks() != null ? getTracks().hashCode() : 0);
        result = 31 * result + getPopularity();
        return result;
    }
}
