package win.knutzuidema.spotifyalarm.api;

import com.sun.istack.internal.Nullable;
import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Artist;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingAlbum;
import win.knutzuidema.spotifyalarm.datatypes.Track;
import win.knutzuidema.spotifyalarm.enums.AlbumType;

import java.util.LinkedList;
import java.util.List;

public class ArtistAPI {

    public Artist getArtist(String id){

        HttpUriRequest request = API
                .requestBuilder("GET", "/artists/" + id)
                .build();

        return new Artist(API.getJSON(request));
    }

    public List<Artist> getArtists(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 artists");
        }

        StringBuilder value = new StringBuilder();
        for(int i = 0; i < ids.length; i++){
            value.append(ids[i]);
            if(i < ids.length - 1){
                value.append(',');
            }
        }

        HttpUriRequest request = API
                .requestBuilder("GET", "/artists")
                .addParameter("ids", value.toString())
                .build();

        JSONArray array = API.getJSON(request)
                .getJSONArray("artists");

        List<Artist> artists = new LinkedList<>();
        for(Object artist : array){
            artists.add(new Artist((JSONObject) artist));
        }

        return artists;
    }

    public List<Artist> getRelatedArtists(String id){
        HttpUriRequest request = API
                .requestBuilder("GET", "/artists/" + id + "/related-artists")
                .build();

        JSONArray array = API.getJSON(request)
                .getJSONArray("artists");

        List<Artist> artists = new LinkedList<>();
        for(Object artist : array){
            artists.add(new Artist((JSONObject) artist));
        }
        return artists;
    }

    public PagingAlbum getAlbums(String id, @Nullable AlbumType... types){
        StringBuilder value = new StringBuilder();
        if(types != null){
            for(int i = 0; i < types.length; i++) {
                value.append(types[i].toString());
                if(i < types.length - 1){
                    value.append(',');
                }
            }
        }

        HttpUriRequest request = API
                .requestBuilder("GET", "/artists/" + id + "/albums")
                .addParameter("album_type", value.toString())
                .build();

        return new PagingAlbum(API.getJSON(request));

    }

    public List<Track> getTopTracks(String id){

        HttpUriRequest request = API
                .requestBuilder("GET", "/artists/" + id + "top-tracks")
                .build();

        JSONArray array = API.getJSON(request)
                .getJSONArray("tracks");

        List<Track> tracks = new LinkedList<>();
        for(Object track : array){
            tracks.add(new Track((JSONObject) track));
        }

        return tracks;
    }
}
