package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.datatypes.Player;
import win.knutzuidema.spotifyalarm.enums.Config;

import java.io.InputStreamReader;

public class PlayerAPI {

    private Authentication auth = new Authentication();

    public JSONObject getAvailableDevices(){

        return API.getJSONfromResponse(
                API.getResponse(
                        API.getBasicRequest(
                                new HttpGet(), "/me/player/devices")));

    }

    public String getIDForName(String name){
        JSONArray devices = getAvailableDevices().getJSONArray("devices");
        for(Object device : devices){
            try{
                JSONObject json = (JSONObject) device;
                String deviceName = json.getString("name");
                if(deviceName.equals(name)){
                    return json.getString("id");
                }
            }catch(Exception ignored){}
        }
        return null;
    }

    public void play(){
        API.getResponse(
                API.getBasicRequest(
                        new HttpPut(), "/me/player/play"));
    }

    public void pause(){
        API.getResponse(
                API.getBasicRequest(
                        new HttpPut(), "/me/player/pause"));
    }

    public void next(){
        API.getResponse(
                API.getBasicRequest(
                        new HttpPost(), "/me/player/next"));
    }

    public void previous(){
        API.getResponse(
                API.getBasicRequest(
                        new HttpPost(), "/me/player/previous"));
    }

    public boolean isPlaying(){
        return getActivePlayer().isPlaying();
    }

    public Player getActivePlayer(){
        return new Player(API.getJSONfromResponse(
                API.getResponse(API.getBasicRequest(
                        new HttpGet(), "/me/player"))));
    }
}
