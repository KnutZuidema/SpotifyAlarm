package win.knutzuidema.spotifyalarm.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.*;
import org.json.JSONArray;
import win.knutzuidema.spotifyalarm.datatypes.*;
import win.knutzuidema.spotifyalarm.datatypes.paging.CursorPagingPlayHistory;
import win.knutzuidema.spotifyalarm.enums.RepeatState;

import java.util.LinkedList;
import java.util.List;

public class PlayerAPI {

    public CursorPagingPlayHistory getRecentlyPlayed(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/player/recently-played")
                .build();

        return new CursorPagingPlayHistory(API.getJSON(request));
    }

    public List<Device> getAvailableDevices(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/player/devices")
                .build();

        JSONArray array = API.getJSON(request)
                .getJSONArray("devices");

        List<Device> devices = new LinkedList<>();
        for(Object device : array){
            devices.add((Device) device);
        }

        return devices;
    }

    public Player getPlayer(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/player")
                .build();

        return new Player(API.getJSON(request));
    }

    public CurrentlyPlaying getCurrentlyPlaying(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/player/currently-playing")
                .build();

        HttpResponse response = API.getResponse(request);
        if(response.getStatusLine().getStatusCode() == 204){
            return null;
        }
        return new CurrentlyPlaying(API.getJSONfromResponse(response));
    }

    public void play(){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/player/play")
                .build();

        API.getResponse(request);
    }

    public void play(SpotifyObject object){
        if(object instanceof User){
            return;
        }

        HttpUriRequest request1 = API
                .requestBuilder("PUT", "/me/player/play")
                .addHeader("Content-Type", "application/json")
                .setEntity(JSONFormEntity
                        .create()
                        .addParameter(object instanceof Track ? "uris" : "context_uri",
                                object instanceof Track ? new JSONArray().put(object.getUri()) : object.getUri())
                        .build())
                .build();

        API.getResponse(request1);
    }

    public void pause(){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/player/pause")
                .build();

        API.getResponse(request);
    }

    public void next(){
        HttpUriRequest request = API
                .requestBuilder("POST", "/me/player/next")
                .build();

        API.getResponse(request);
    }

    public void previous(){
        HttpUriRequest request = API
                .requestBuilder("POST", "/me/player/previous")
                .build();

        API.getResponse(request);
    }

    public void seek(int ms){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/player/seek?position_ms=" + ms)
                .build();

        API.getResponse(request);
    }

    public void setRepeat(RepeatState state){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/player/repeat?state=" + state.toString())
                .build();

        API.getResponse(request);
    }

    public void setVolume(int percent){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/player/volume?volume_percent=" + percent)
                .build();

        API.getResponse(request);
    }

    public void setShuffle(boolean state){
        HttpUriRequest request = API
                .requestBuilder("PUT", "/me/player/shuffle?state=" + state)
                .build();

        API.getResponse(request);
    }
}
