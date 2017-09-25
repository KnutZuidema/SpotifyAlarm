package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONArray;
import win.knutzuidema.spotifyalarm.datatypes.User;
import win.knutzuidema.spotifyalarm.datatypes.paging.*;
import win.knutzuidema.spotifyalarm.enums.TimeFrame;

import java.util.Arrays;

public class MeAPI {

    public static User getMe(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me")
                .build();

        return new User(API.getJSON(request));
    }

    public static CursorPagingArtist getFollowedAtists(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/following?type=artist")
                .build();

        return new CursorPagingArtist(API.getJSON(request));
    }

    public static void followArtists(String... artistIDs){
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

    public static void followUsers(String... userIDs){
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

    public static void unfollowArtists(String... artistIDs){
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

    public static void unfollowUsers(String... userIDs){
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

    public static boolean isFollowingArtist(String artistID){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/following/contains?type=artist")
                .addParameter("ids", artistID)
                .build();

        return API.getArrayFromResponse(API.getResponse(request)).getBoolean(0);
    }

    public static boolean isFollowingUser(String userID){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/following/contains?type=user")
                .addParameter("ids", userID)
                .build();

        return API.getArrayFromResponse(API.getResponse(request)).getBoolean(0);
    }

    public static void saveTracks(String... ids){
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

    public static PagingTrack getSavedTracks(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/tracks")
                .build();

        return new PagingTrack(API
                .getJSON(request));
    }

    public static void removeSavedTracks(String... ids){
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

    public static boolean savedTracksContain(String id){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/tracks/contains")
                .addParameter("ids", id)
                .build();

        return API
                .getArrayFromResponse(API.getResponse(request))
                .getBoolean(0);
    }

    public static void saveAlbums(String... ids){
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

    public static PagingSavedAlbum getSavedAlbums(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/albums")
                .build();

        return new PagingSavedAlbum(API
                .getJSON(request));
    }

    public static void deleteSavedAlbums(String... ids){
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

    public static boolean savedAlbumsContain(String id){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/albums/contains")
                .addParameter("ids", id)
                .build();

        return API
                .getArrayFromResponse(API.getResponse(request))
                .getBoolean(0);
    }

    public static PagingArtist getTopArtists(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/top?type=artists")
                .build();

        return new PagingArtist(API.getJSON(request));
    }

    public static PagingArtist getTopArtists(TimeFrame timeframe){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/top?type=artists")
                .addParameter("time_range", timeframe.toString())
                .build();

        return new PagingArtist(API.getJSON(request));
    }

    public static PagingTrack getTopTracks(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/top?type=tracks")
                .build();

        return new PagingTrack(API.getJSON(request));
    }

    public static PagingTrack getTopTracks(TimeFrame timeframe){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/top?type=tracks")
                .addParameter("time_range", timeframe.toString())
                .build();

        return new PagingTrack(API.getJSON(request));
    }

    public static PagingPlaylist getPlaylists(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/playlists")
                .build();

        return new PagingPlaylist(API.getJSON(request));
    }
}
