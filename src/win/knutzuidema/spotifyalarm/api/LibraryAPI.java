package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
import win.knutzuidema.spotifyalarm.datatypes.PagingAlbum;
import win.knutzuidema.spotifyalarm.datatypes.PagingTrack;

public class LibraryAPI{

    public void saveTracks(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 tracks");
        }else if(ids.length < 1){
            return;
        }
        StringBuilder value = new StringBuilder();
        for(int i = 0; i < ids.length; i++){
            value.append(ids[i]);
            if(i < ids.length - 1){
                value.append(',');
            }
        }

        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/tracks")
                .addHeader("Content-Type", "application/json")
                .addParameter("ids", value.toString())
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

    public void deleteSavedTracks(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 tracks");
        }else if(ids.length < 1){
            return;
        }

        StringBuilder value = new StringBuilder();
        for(int i = 0; i < ids.length; i++){
            value.append(ids[i]);
            if(i < ids.length - 1){
                value.append(',');
            }
        }

        HttpUriRequest request = API
                .requestBuilder("DELETE", "/me/tracks")
                .addParameter("ids", value.toString())
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
        StringBuilder value = new StringBuilder();
        for(int i = 0; i < ids.length; i++){
            value.append(ids[i]);
            if(i < ids.length - 1){
                value.append(',');
            }
        }

        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/albums")
                .addHeader("Content-Type", "application/json")
                .addParameter("ids", value.toString())
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

        StringBuilder value = new StringBuilder();
        for(int i = 0; i < ids.length; i++){
            value.append(ids[i]);
            if(i < ids.length - 1){
                value.append(',');
            }
        }

        HttpUriRequest request = API
                .requestBuilder("DELETE", "/me/albums")
                .addParameter("ids", value.toString())
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
}
