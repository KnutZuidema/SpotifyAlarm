package win.knutzuidema.spotifyalarm;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.enums.Config;

import java.io.InputStreamReader;

public class Player {

    private Auth auth = new Auth();

    public Auth getAuth(){
        return auth;
    }

    public JSONObject getAvailableDevices(){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet get = new HttpGet(Config.API_URI.toString() + "/me/player/devices");
        get.addHeader(getAuth().bearerAuth());

        try(CloseableHttpResponse response = httpClient.execute(get)){
            System.out.println(response.getStatusLine());
            InputStreamReader isr = new InputStreamReader(response.getEntity().getContent());
            StringBuilder string = new StringBuilder();
            while(isr.ready()){
                string.append((char) isr.read());
            }
            System.out.println(string);
            return new JSONObject(string.toString());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
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

    public boolean play(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut play = new HttpPut(Config.API_URI.toString() + "/me/player/play");

        play.addHeader(getAuth().bearerAuth());


        try(CloseableHttpResponse response = httpClient.execute(play)){
            return response.getStatusLine().getStatusCode() == 204;
        }catch(Exception e){
            return false;
        }
    }

    public boolean pause(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPut pause = new HttpPut(Config.API_URI.toString() + "/me/player/pause");

        pause.addHeader(getAuth().bearerAuth());


        try(CloseableHttpResponse response = httpClient.execute(pause)){
            return response.getStatusLine().getStatusCode() == 204;
        }catch(Exception e){
            return false;
        }
    }

    public boolean next(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost next = new HttpPost(Config.API_URI.toString() + "/me/player/next");

        next.addHeader(getAuth().bearerAuth());


        try(CloseableHttpResponse response = httpClient.execute(next)){
            return response.getStatusLine().getStatusCode() == 204;
        }catch(Exception e){
            return false;
        }
    }

    public boolean previous(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost previous = new HttpPost(Config.API_URI.toString() + "/me/player/previous");

        previous.addHeader(getAuth().bearerAuth());


        try(CloseableHttpResponse response = httpClient.execute(previous)){
            return response.getStatusLine().getStatusCode() == 204;
        }catch(Exception e){
            return false;
        }
    }

    public boolean isPlaying(){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet(Config.API_URI.toString() + "/me/player");
        get.addHeader(getAuth().bearerAuth());

        try(CloseableHttpResponse response = httpClient.execute(get)){
            InputStreamReader isr = new InputStreamReader(response.getEntity().getContent());
            StringBuilder string = new StringBuilder();
            while(isr.ready()){
                string.append((char) isr.read());
            }
            JSONObject json = new JSONObject(string.toString());
            return  json.getBoolean("is_playing");
        }catch(Exception e){
            return false;
        }
    }
}
