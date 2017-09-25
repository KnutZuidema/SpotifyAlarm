package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
import win.knutzuidema.spotifyalarm.datatypes.*;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingAlbum;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingCategory;
import win.knutzuidema.spotifyalarm.datatypes.paging.PagingPlaylist;

public class BrowseAPI {

    public static PagingPlaylist getFeaturedPlaylists(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/browse/featured-playlists")
                .build();

        return new PagingPlaylist(API
                .getJSON(request)
                .getJSONObject("playlists"));
    }

    public static PagingAlbum getNewReleases() {
        HttpUriRequest request = API
                .requestBuilder("GET", "/browse/new-releases")
                .build();

        return new PagingAlbum(API
                .getJSON(request)
                .getJSONObject("albums"));
    }

    public static PagingCategory getCategoriesList(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/browse/categories")
                .build();

        return new PagingCategory(API
                .getJSON(request)
                .getJSONObject("categories"));
    }

    public static Category getCategory(String id){
        HttpUriRequest request = API
                .requestBuilder("GET", "/browse/categories/" + id)
                .build();

        return new Category(API
                .getJSON(request));
    }

    public static PagingPlaylist getCategoryPlaylists(String id){
        HttpUriRequest request = API
                .requestBuilder("GET", "/browse/categories/" + id + "/playlists")
                .build();

        return new PagingPlaylist(API
                .getJSON(request)
                .getJSONObject("playlists"));
    }
}
