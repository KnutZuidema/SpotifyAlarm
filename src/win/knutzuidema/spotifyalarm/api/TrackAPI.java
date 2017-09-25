package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Track;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingTrack;

import java.util.LinkedList;
import java.util.List;

public class TrackAPI {

    public static Track getTrack(String id){
        HttpUriRequest request = API
                .requestBuilder("GET", "/tracks/" + id)
                .build();

        return new Track(API.getJSON(request));
    }

    public static List<Track> getTracks(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 tracks per request");
        }

        StringBuilder value = new StringBuilder();
        for (int i = 0; i < ids.length; i++) {
            value.append(ids[i]);
            if (i < ids.length - 1) {
                value.append(',');
            }
        }

        HttpUriRequest request = API
                .requestBuilder("GET", "/tracks")
                .addParameter("ids", value.toString())
                .build();

        JSONArray array = API.getJSON(request).getJSONArray("tracks");
        List<Track> tracks = new LinkedList<>();

        for(Object object : array){
            tracks.add((new Track((JSONObject) object)));
        }

        return tracks;
    }
}
