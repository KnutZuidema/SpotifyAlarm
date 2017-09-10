package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Album;
import win.knutzuidema.spotifyalarm.datatypes.PagingAlbum;
import win.knutzuidema.spotifyalarm.datatypes.PagingTrack;
import win.knutzuidema.spotifyalarm.datatypes.Track;
import win.knutzuidema.spotifyalarm.enums.AlbumType;

import java.util.LinkedList;
import java.util.List;

public class AlbumAPI {

    public Album getAlbum(String id){
         return new Album(
                 API.getJSONfromResponse(
                         API.getResponse(
                                 API.getBasicRequest(
                                         new HttpGet(), "/albums/" + id))));
    }

    public List<Album> getAlbums(String... ids){
        if(ids.length > 20){
            throw new IllegalArgumentException("max of 20 albums");
        }

        StringBuilder endpoint = new StringBuilder();
        endpoint.append("/albums?ids=");
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
                .getJSONArray("albums");

        List<Album> albums = new LinkedList<>();
        for(Object album : array){
            albums.add(new Album((JSONObject) album));
        }

        return albums;
    }

    public PagingTrack getTracks(String id){
        return getAlbum(id).getTracks();
    }

    public PagingAlbum getNewReleases(){
        return new PagingAlbum(API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), "/browse/new-releases")))
                .getJSONObject("albums"));
    }

    public void saveAlbums(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 albums");
        }else if(ids.length < 1){
            return;
        }
        StringBuilder endpoint = new StringBuilder();
        endpoint.append("/me/albums?ids=");
        for(int i = 0; i < ids.length; i++){
            endpoint.append(ids[i]);
            if(i < ids.length - 1){
                endpoint.append(',');
            }
        }

        API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpPut(), endpoint.toString())));
    }

    public JSONObject getSavedAlbums(int limit, int offset){
        if(limit < 1 || limit > 50){
            throw new IllegalArgumentException("limit must be between 1 and 50");
        }
        return API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpPut(), "/me/albums?limit=" + limit + "&offset=" + offset)));
    }

    public void deleteSavedAlbums(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 albums");
        }else if(ids.length < 1){
            return;
        }

        StringBuilder endpoint = new StringBuilder();
        endpoint.append("/me/albums?ids=");
        for(int i = 0; i < ids.length; i++){
            endpoint.append(ids[i]);
            if(i < ids.length - 1){
                endpoint.append(',');
            }
        }

        API.getResponse(
                API.getBasicRequest(
                        new HttpDelete(), endpoint.toString()));
    }

    public JSONArray savedAlbumsContain(String... ids){
        if(ids.length > 50){
            throw new IllegalArgumentException("max of 50 albums");
        }else if(ids.length < 1){
            throw new IllegalArgumentException("needs at least 1 argument");
        }

        StringBuilder endpoint = new StringBuilder();
        endpoint.append("/me/albums/contains?ids=");
        for(int i = 0; i < ids.length; i++){
            endpoint.append(ids[i]);
            if(i < ids.length - 1){
                endpoint.append(',');
            }
        }

        return API.getArrayFromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), endpoint.toString())));
    }
}
