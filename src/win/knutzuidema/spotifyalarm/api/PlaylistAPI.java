package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
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
}
