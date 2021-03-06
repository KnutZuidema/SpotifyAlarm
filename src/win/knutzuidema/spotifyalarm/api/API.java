package win.knutzuidema.spotifyalarm.api;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONArray;
import org.json.JSONObject;
import static win.knutzuidema.spotifyalarm.enums.Config.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Iterator;

public class API {

    public static HttpResponse getResponse(HttpUriRequest request){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try{
            return httpClient.execute(request);
        }catch(IOException e){
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
            System.out.println(response.getStatusLine());
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

    public static JSONObject getJSON(HttpUriRequest request){
        return getJSONfromResponse(getResponse(request));
    }

    public static RequestBuilder requestBuilder(String method, String uri){
        return RequestBuilder.create(method).setUri(API_URI + uri).addHeader(Authentication.bearerAuth());
    }

    public static String toQueryString(Collection<?> collection){
        StringBuilder stringBuilder = new StringBuilder();
        Object[] array = collection.toArray();
        for(int i = 0; i < array.length; i++){
            stringBuilder.append(array[i]);
            if(i < array.length - 1){
                stringBuilder.append(',');
            }
        }
        return stringBuilder.toString();
    }
}
