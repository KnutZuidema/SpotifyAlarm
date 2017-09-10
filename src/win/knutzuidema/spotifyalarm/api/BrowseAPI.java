package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpGet;
import win.knutzuidema.spotifyalarm.datatypes.PagingAlbum;
import win.knutzuidema.spotifyalarm.datatypes.PagingPlaylist;
import win.knutzuidema.spotifyalarm.datatypes.Playlist;

public class BrowseAPI {

    public PagingPlaylist getFeaturedPlaylists(){
        return new PagingPlaylist(
                API.getJSONfromResponse(
                        API.getResponse(
                                API.getBasicRequest(
                                        new HttpGet(), "/browse/featured-playlists")))
                        .getJSONObject("playlists"));
    }

    public PagingAlbum getNewReleases(){
        return new PagingAlbum(
                API.getJSONfromResponse(
                        API.getResponse(
                                API.getBasicRequest(
                                        new HttpGet(), "/browse/new-releases")))
                        .getJSONObject("albums"));
    }


}
