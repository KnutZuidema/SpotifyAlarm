package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONArray;
import win.knutzuidema.spotifyalarm.datatypes.paging.*;
import win.knutzuidema.spotifyalarm.datatypes.User;
import win.knutzuidema.spotifyalarm.enums.TimeFrame;

import java.util.Arrays;

public class MeAPI {

    public User getMe(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me")
                .build();

        return new User(API.getJSON(request));
    }

    public CursorPagingArtist getFollowedAtists(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/following?type=artist")
                .build();

        return new CursorPagingArtist(API.getJSON(request));
    }

    public void followArtists(String... artistIDs){
        if(artistIDs.length > 50){
            return;
        }

        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/following?type=artist")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("ids", new JSONArray(artistIDs))
                        .build())
                .build();

        API.getResponse(request);
    }

    public void followUsers(String... userIDs){
        if(userIDs.length > 50){
            return;
        }

        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/following?type=user")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("ids", new JSONArray(userIDs))
                        .build())
                .build();

        API.getResponse(request);
    }

    public void unfollowArtists(String... artistIDs){
        if(artistIDs.length > 50){
            return;
        }

        HttpUriRequest request = API
                .requestBuilder("DELETE", "/me/following?type=artist")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("ids", new JSONArray(artistIDs))
                        .build())
                .build();

        API.getResponse(request);
    }

    public void unfollowUsers(String... userIDs){
        if(userIDs.length > 50){
            return;
        }

        HttpUriRequest request = API
                .requestBuilder("DELETE", "/me/following?type=user")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("ids", new JSONArray(userIDs))
                        .build())
                .build();

        API.getResponse(request);
    }

    public boolean isFollowingArtist(String artistID){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/following/contains?type=artist")
                .addParameter("ids", artistID)
                .build();

        return API.getArrayFromResponse(API.getResponse(request)).getBoolean(0);
    }

    public boolean isFollowingUser(String userID){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/following/contains?type=user")
                .addParameter("ids", userID)
                .build();

        return API.getArrayFromResponse(API.getResponse(request)).getBoolean(0);
    }

    public void saveTracks(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 tracks");
        }else if(ids.length < 1){
            return;
        }

        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/tracks")
                .addHeader("Content-Type", "application/json")
                .addParameter("ids", API.toQueryString(Arrays.asList(ids)))
                .build();

        API.getResponse(request);
    }

    public PagingTrack getSavedTracks(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/tracks")
                .build();

        return new PagingTrack(API
                .getJSON(request));
    }

    public void removeSavedTracks(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 tracks");
        }else if(ids.length < 1){
            return;
        }

        HttpUriRequest request = API
                .requestBuilder("DELETE", "/me/tracks")
                .addParameter("ids", API.toQueryString(Arrays.asList(ids)))
                .build();

        API.getResponse(request);
    }

    public boolean savedTracksContain(String id){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/tracks/contains")
                .addParameter("ids", id)
                .build();

        return API
                .getArrayFromResponse(API.getResponse(request))
                .getBoolean(0);
    }

    public void saveAlbums(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 albums");
        }else if(ids.length < 1){
            return;
        }

        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/albums")
                .addHeader("Content-Type", "application/json")
                .addParameter("ids", API.toQueryString(Arrays.asList(ids)))
                .build();

        API.getResponse(request);
    }

    public PagingAlbum getSavedAlbums(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/albums")
                .build();

        return new PagingAlbum(API
                .getJSON(request));
    }

    public void deleteSavedAlbums(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 albums");
        }else if(ids.length < 1){
            return;
        }

        HttpUriRequest request = API
                .requestBuilder("DELETE", "/me/albums")
                .addParameter("ids", API.toQueryString(Arrays.asList(ids)))
                .build();

        API.getResponse(request);
    }

    public boolean savedAlbumsContain(String id){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/albums/contains")
                .addParameter("ids", id)
                .build();

        return API
                .getArrayFromResponse(API.getResponse(request))
                .getBoolean(0);
    }

    public PagingArtist getTopArtists(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/top?type=artists")
                .build();

        return new PagingArtist(API.getJSON(request));
    }

    public PagingArtist getTopArtists(TimeFrame timeframe){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/top?type=artists")
                .addParameter("time_range", timeframe.toString())
                .build();

        return new PagingArtist(API.getJSON(request));
    }

    public PagingTrack getTopTracks(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/top?type=tracks")
                .build();

        return new PagingTrack(API.getJSON(request));
    }

    public PagingTrack getTopTracks(TimeFrame timeframe){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/top?type=tracks")
                .addParameter("time_range", timeframe.toString())
                .build();

        return new PagingTrack(API.getJSON(request));
    }

    public PagingPlaylist getPlaylists(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/playlists")
                .build();

        return new PagingPlaylist(API.getJSON(request));
    }
}
