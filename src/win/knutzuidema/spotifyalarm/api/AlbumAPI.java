package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.*;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Album;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingTrack;

import java.util.LinkedList;
import java.util.List;

public class AlbumAPI {

    public static Album getAlbum(String id) {
        HttpUriRequest request = API
                .requestBuilder("GET", "/albums/" + id)
                .build();

        return new Album(API.getJSON(request));
    }

    public static List<Album> getAlbums(String... ids) {
        if (ids.length > 20) {
            throw new IllegalArgumentException("max of 20 albums");
        }

        StringBuilder value = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            value.append(ids[i]);
            if (i < ids.length - 1) {
                value.append(',');
            }
        }

        HttpUriRequest request = API.requestBuilder("GET", "/albums")
                .addParameter("ids", value.toString())
                .build();

        JSONArray array = API.getJSON(request).getJSONArray("albums");

        List<Album> albums = new LinkedList<>();
        for (Object album : array) {
            albums.add(new Album((JSONObject) album));
        }

        return albums;
    }
}
