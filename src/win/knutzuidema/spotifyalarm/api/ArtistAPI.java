package win.knutzuidema.spotifyalarm.api;

import com.sun.istack.internal.Nullable;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Artist;
import win.knutzuidema.spotifyalarm.datatypes.PagingAlbum;
import win.knutzuidema.spotifyalarm.datatypes.Track;
import win.knutzuidema.spotifyalarm.enums.AlbumType;

import java.util.LinkedList;
import java.util.List;

public class ArtistAPI {

    public Artist getArtist(String id){
        return new Artist(
                API.getJSONfromResponse(
                        API.getResponse(
                                API.getBasicRequest(
                                        new HttpGet(), "/artists/" + id))));
    }

    public List<Artist> getArtists(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 artists");
        }

        StringBuilder endpoint = new StringBuilder();
        endpoint.append("/artists?ids=");
        for(int i = 0; i < ids.length; i++){
            endpoint.append(ids[i]);
            if(i < ids.length - 1){
                endpoint.append(',');
            }
        }

        JSONArray array = API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), endpoint.toString())))
                .getJSONArray("artists");

        List<Artist> artists = new LinkedList<>();
        for(Object artist : array){
            artists.add(new Artist((JSONObject) artist));
        }

        return artists;
    }

    public List<Artist> getRelatedArtists(String id){
        JSONArray array = API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), "/artists/" + id + "/related-artists")))
                .getJSONArray("artists");

        List<Artist> artists = new LinkedList<>();
        for(Object artist : array){
            artists.add(new Artist((JSONObject) artist));
        }
        return artists;
    }

    public PagingAlbum getAlbums(String id, @Nullable AlbumType... types){
        StringBuilder endpoint = new StringBuilder();
        endpoint.append("/artists/").append(id).append("/albums");
        if(types != null){
            endpoint.append("?album_type=");
            for(int i = 0; i < types.length; i++) {
                endpoint.append(types[i].toString());
                if(i < types.length - 1){
                    endpoint.append(',');
                }
            }
        }

        return new PagingAlbum(
                API.getJSONfromResponse(
                        API.getResponse(
                                API.getBasicRequest(
                                        new HttpGet(), endpoint.toString()))));

    }

    public List<Track> getTopTracks(String id){
        JSONArray array = API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), id + "/top-tracks")))
                .getJSONArray("tracks");

        List<Track> tracks = new LinkedList<>();
        for(Object track : array){
            tracks.add(new Track((JSONObject) track));
        }

        return tracks;
    }
}
