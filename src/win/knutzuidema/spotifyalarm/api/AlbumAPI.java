package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPut;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.enums.AlbumType;

public class AlbumAPI {

    public JSONObject getAlbum(String id){
         return API.getJSONfromResponse(
                 API.getResponse(
                         API.getBasicRequest(
                                 new HttpGet(), "/albums/" + id)));
    }

    public JSONObject getAlbums(String... ids){
        if(ids.length > 20){
            throw new IllegalArgumentException("max of 20 albums");
        }
        StringBuilder endpoint = new StringBuilder();
        endpoint.append("?ids=");
        for(int i = 0; i < ids.length; i++){
            endpoint.append(ids[i]);
            if(i < ids.length - 1){
                endpoint.append(',');
            }
        }

        return getAlbum(endpoint.toString());
    }

    public JSONObject getTracks(String id){
        return getAlbum(id + "/tracks");
    }

    public JSONObject getTracks(String id, int limit, int offset){
        if(limit < 1 || limit > 50){
            throw new IllegalArgumentException("limit must be between 1 and 50");
        }
        return getTracks(id + "?limit=" + limit + "&offset=" + offset);
    }

    public JSONObject getArtistAlbums(String id){
        return API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), "/artist/" + id + "/albums")));
    }

    public JSONObject getArtistAlbums(String id, AlbumType... types){
        StringBuilder endpoint = new StringBuilder();
        endpoint.append("/artists/").append(id).append("/albums").append("?album_type=");
        for(int i = 0; i < types.length; i++){
            endpoint.append(types[i].toString());
            if(i < types.length - 1){
                endpoint.append(',');
            }
        }

        return API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), endpoint.toString())));
    }

    public JSONObject getArtistAlbums(String id, int limit, int offset){
        if(limit < 1 || limit > 50){
            throw new IllegalArgumentException("limit must be between 1 and 50");
        }

        return API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), "/artists/" + id + "/albums" +
                                        "?limit=" + limit + "&offset=" + offset)));
    }

    public JSONObject getArtistAlbums(String id, int limit, int offset, AlbumType... types){
        if(limit < 1 || limit > 50){
            throw new IllegalArgumentException("limit must be between 1 and 50");
        }
        StringBuilder endpoint = new StringBuilder();
        endpoint.append("/artists/").append(id).append("/albums").append("?album_type=");
        for(int i = 0; i < types.length; i++){
            endpoint.append(types[i].toString());
            if(i < types.length - 1){
                endpoint.append(',');
            }
        }
        endpoint.append("&limit=").append(limit).append("&offset=").append(offset);

        return API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), endpoint.toString())));
    }

    public JSONObject getNewReleases(){
        return API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), "/browse/new-releases")));
    }

    public JSONObject getNewReleases(int limit, int offset){
        if(limit < 1 || limit > 50){
            throw new IllegalArgumentException("limit must be between 1 and 50");
        }

        String endpoint = "/browse/new-releases" +
                "?limit=" + limit +
                "&offset=" + offset;

        return API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), endpoint)));
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

        API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpDelete(), endpoint.toString())));
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
