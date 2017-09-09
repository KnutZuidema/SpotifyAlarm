package win.knutzuidema.spotifyalarm;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.enums.Config;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;

public class Alarm {

    public static HttpResponse getResponse(HttpRequestBase request){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try{
            return httpClient.execute(request);
        }catch(IOException e){
            return null;
        }
    }

    public static <T extends HttpRequestBase> T getBasicRequest(T type, String endpoint){
        try {
            type.setURI(new URI(Config.API_URI.toString() + endpoint));

            type.addHeader(new Auth().bearerAuth());

            return type;
        }catch(Exception e){
            return null;
        }
    }

    public static JSONObject getJSONfromResponse(HttpResponse response){
        try(InputStreamReader isr = new InputStreamReader(response.getEntity().getContent())){
            StringBuilder string = new StringBuilder();
            while(isr.ready()){
                string.append((char) isr.read());
            }
            return new JSONObject(string.toString());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static JSONArray getArrayFromResponse(HttpResponse response){
        try(InputStreamReader isr = new InputStreamReader(response.getEntity().getContent())){
            StringBuilder string = new StringBuilder();
            while(isr.ready()){
                string.append((char) isr.read());
            }
            return new JSONArray(string.toString());
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
