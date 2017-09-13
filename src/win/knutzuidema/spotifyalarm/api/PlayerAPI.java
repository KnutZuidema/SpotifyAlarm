package win.knutzuidema.spotifyalarm.api;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.*;
import win.knutzuidema.spotifyalarm.enums.ContextType;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlayerAPI {

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

    public Device getDeviceForName(String name){
        List<Device> devices = getAvailableDevices();
        for(Device device : devices){
            if(device.getName().equals(name)){
                return device;
            }
        }
        return null;
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

    public boolean isPlaying(){
        return getPlayer().isPlaying();
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

        return new CurrentlyPlaying(API.getJSON(request));
    }
}
