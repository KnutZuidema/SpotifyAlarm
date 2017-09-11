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
        HttpPut request = new HttpPut("https://api.spotify.com/v1/me/player/play");
        request.addHeader(Authentication.bearerAuth());
        request.addHeader("Content-Type", "application/x-www-form-urlencoded");
        if(object instanceof Track) {
            StringEntity entity = new StringEntity("{\"uris\": [\"" + object.getUri() + "\"]}", ContentType.APPLICATION_FORM_URLENCODED);
            request.setEntity(entity);
        }else if(object instanceof Album || object instanceof Playlist || object instanceof Artist){
            StringEntity entity = new StringEntity("{\"context_uri\": \"" + object.getUri() + "\"}", ContentType.APPLICATION_FORM_URLENCODED);
            request.setEntity(entity);
        }else{
            return;
        }

        API.getResponse(request);
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
}
