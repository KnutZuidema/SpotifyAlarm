package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
import win.knutzuidema.spotifyalarm.datatypes.PagingPlaylistTrack;

public class PlaylistAPI {

    public PagingPlaylistTrack getPlaylistTracks(String userID, String playlistID){
        HttpUriRequest request = API
                .requestBuilder("GET", "/users/" + userID + "/playlists/" + playlistID + "/tracks")
                .build();

        return new PagingPlaylistTrack(API.getJSON(request));
    }
}
