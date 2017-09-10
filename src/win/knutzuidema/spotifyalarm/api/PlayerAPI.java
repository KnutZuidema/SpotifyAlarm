package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.*;
import org.json.JSONArray;
import win.knutzuidema.spotifyalarm.datatypes.Device;
import win.knutzuidema.spotifyalarm.datatypes.Player;

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
        return getActivePlayer().isPlaying();
    }

    public Player getActivePlayer(){
        HttpUriRequest request = API
                .requestBuilder("GET", "/me/player")
                .build();

        return new Player(API.getJSON(request));
    }
}
