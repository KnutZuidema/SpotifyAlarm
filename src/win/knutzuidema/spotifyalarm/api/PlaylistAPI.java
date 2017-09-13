package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
import win.knutzuidema.spotifyalarm.datatypes.PagingPlaylistTrack;
import win.knutzuidema.spotifyalarm.datatypes.Playlist;

public class PlaylistAPI {

    public PagingPlaylistTrack getPlaylistTracks(String userID, String playlistID){
        HttpUriRequest request = API
                .requestBuilder("GET", "/users/" + userID + "/playlists/" + playlistID + "/tracks")
                .build();

        return new PagingPlaylistTrack(API.getJSON(request));
    }

    public Playlist getPlaylist(String userID, String playlistID){
        HttpUriRequest request = API
                .requestBuilder("GET", "/users/" + userID + "/playlists/" + playlistID)
                .build();

        return new Playlist(API.getJSON(request));
    }

    public Playlist createPlaylist(String userID, String name){
        HttpUriRequest request = API
                .requestBuilder("POST", "/users/" + userID + "/playlists")
                .addHeader("Content-Type", "application/json")
                .build();

        return new Playlist(API.getJSON(request));
    }

    public Playlist createPlaylist(String userID, String name, boolean isPublic, boolean isCollaborative, String description){
        HttpUriRequest request = API
                .requestBuilder("POST", "/users/" + userID + "/playlists")
                .addHeader("Content-Type", "application/json")
                .addParameter("name", name)
                .build();

        return new Playlist(API.getJSON(request));
    }
}
