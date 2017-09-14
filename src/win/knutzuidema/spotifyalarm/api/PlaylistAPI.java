package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingPlaylist;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingPlaylistTrack;
import win.knutzuidema.spotifyalarm.datatypes.Playlist;

public class PlaylistAPI {

    public Playlist getPlaylist(String userID, String playlistID){
        HttpUriRequest request = API
                .requestBuilder("GET", "/users/" + userID + "/playlists/" + playlistID)
                .build();

        return new Playlist(API.getJSON(request));
    }

    public PagingPlaylistTrack getPlaylistTracks(String userID, String playlistID){
        HttpUriRequest request = API
                .requestBuilder("GET", "/users/" + userID + "/playlists/" + playlistID + "/tracks")
                .build();

        return new PagingPlaylistTrack(API.getJSON(request));
    }

    public Playlist createPlaylist(String userID, String name){
        HttpUriRequest request = API
                .requestBuilder("POST", "/users/" + userID + "/playlists")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("name", name)
                        .build())
                .build();

        return new Playlist(API.getJSON(request));
    }

    public Playlist createPlaylist(String userID, String name, boolean isPublic, boolean isCollaborative, String description){
        HttpUriRequest request = API
                .requestBuilder("POST", "/users/" + userID + "/playlists")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("name", name)
                        .addParameter("public", isPublic)
                        .addParameter("collaborative", isCollaborative)
                        .addParameter("description", description)
                        .build())
                .build();

        return new Playlist(API.getJSON(request));
    }

    public PagingPlaylist getUserPlaylists(String userID){
        HttpUriRequest request = API
                .requestBuilder("GET", "/usesrs/" + userID + "/playlists")
                .build();

        return new PagingPlaylist(API.getJSON(request));
    }

    public void setName(String userID, String playlistID, String name){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/users/" + userID + "/playlists/" + playlistID)
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("name", name)
                        .build())
                .build();

        API.getResponse(request);
    }

    public void setPublic(String userID, String playlistID, boolean isPublic){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/users/" + userID + "/playlists/" + playlistID)
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("public", isPublic)
                        .build())
                .build();

        API.getResponse(request);
    }

    public void setCollaborative(String userID, String playlistID, boolean isCollaborative){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/users/" + userID + "/playlists/" + playlistID)
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("collaborative", isCollaborative)
                        .build())
                .build();

        API.getResponse(request);
    }

    public void setDescription(String userID, String playlistID, String description){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/users/" + userID + "/playlists/" + playlistID)
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("description", description)
                        .build())
                .build();

        API.getResponse(request);
    }

    public void addTracks(String userID, String playlistID, String... uris){
        if(uris.length > 100){
            throw new IllegalArgumentException("max of 100 tracks");
        }

        HttpUriRequest request = API
                .requestBuilder("POST", "/users/" + userID + "/playlists/" + playlistID + "/tracks")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("uris", uris)
                        .build())
                .build();

        API.getResponse(request);
    }

    public void removeTracks(String userID, String playlistID, String... uris){
        if(uris.length > 100){
            throw new IllegalArgumentException("max of 100 tracks");
        }

        JSONArray array = new JSONArray();
        for(String uri : uris){
            array.put(new JSONObject().put("uri", uri));
        }

        HttpUriRequest request = API
                .requestBuilder("DELETE", "/users/" + userID + "/playlists/" + playlistID + "/tracks")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter("tracks", uris)
                        .build())
                .build();

        API.getResponse(request);
    }

    public boolean isFollowing(String userID, String playlistID, String followerID){
        HttpUriRequest request = API
                .requestBuilder("GET", "/users/" + userID + "/playlists/" + playlistID + "/followers/contains")
                .addParameter("ids", followerID)
                .build();

        return API.getArrayFromResponse(API.getResponse(request)).getBoolean(0);
    }

    //TODO reorder, replace, upload cover image
}
