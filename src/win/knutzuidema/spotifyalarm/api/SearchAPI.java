package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.paging.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class SearchAPI {

    public static Map<String, Paging> search(String query){
        HttpUriRequest request = API
                .requestBuilder("GET", "/search")
                .addParameter("q", query)
                .addParameter("type", "album,artist,track,playlist")
                .build();

        JSONObject json = API.getJSON(request);
        Map<String, Paging> map = new LinkedHashMap<>();
        if(!json.isNull("albums")) {
            map.put("albums", new PagingAlbum(json.getJSONObject("albums")));
        }
        if(!json.isNull("artists")){
            map.put("artists", new PagingArtist(json.getJSONObject("artists")));
        }
        if(!json.isNull("tracks")){
            map.put("tracks", new PagingTrack(json.getJSONObject("tracks")));
        }
        if(!json.isNull("playlists")){
            map.put("playlists", new PagingPlaylist(json.getJSONObject("playlists")));
        }

        return map;
    }

    public static PagingAlbum searchAlbum(String query){
        HttpUriRequest request = API
                .requestBuilder("GET", "/search")
                .addParameter("q", query)
                .addParameter("type", "album")
                .build();

        return new PagingAlbum(API.getJSON(request).getJSONObject("albums"));
    }

    public static PagingArtist searchArtist(String query){
        HttpUriRequest request = API
                .requestBuilder("GET", "/search")
                .addParameter("q", query)
                .addParameter("type", "artist")
                .build();

        return new PagingArtist(API.getJSON(request).getJSONObject("artist"));
    }

    public static PagingTrack searchTrack(String query){
        HttpUriRequest request = API
                .requestBuilder("GET", "/search")
                .addParameter("q", query)
                .addParameter("type", "track")
                .build();

        return new PagingTrack(API.getJSON(request).getJSONObject("tracks"));
    }

    public static PagingPlaylist searchPlaylist(String query){
        HttpUriRequest request = API
                .requestBuilder("GET", "/search")
                .addParameter("q", query)
                .addParameter("type", "playlist")
                .build();

        return new PagingPlaylist(API.getJSON(request).getJSONObject("playlists"));
    }
}
