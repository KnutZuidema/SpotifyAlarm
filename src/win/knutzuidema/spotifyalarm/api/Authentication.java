package win.knutzuidema.spotifyalarm.api;

import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.enums.Config;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

class Authentication {

    private String getRefreshToken(){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost post = new HttpPost("https://accounts.spotify.com/api/token");

        post.addHeader("User-Agent", Config.USER_AGENT.toString());
        post.addHeader(basicAuth());

        List<NameValuePair> nvps = new ArrayList<>();
        nvps.add(new BasicNameValuePair("grant_type", "authorization_code"));
        nvps.add(new BasicNameValuePair("code", Config.ACCESS_CODE.toString()));
        nvps.add((new BasicNameValuePair("redirect_uri", Config.REDIRECT_URI.toString())));

        try {
            post.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));

            CloseableHttpResponse response = httpClient.execute(post);

            System.out.println(response.getStatusLine());
            InputStreamReader isr = new InputStreamReader(response.getEntity().getContent());

            StringBuilder stringResponse = new StringBuilder();
            while(isr.ready()){
                stringResponse.append((char) isr.read());
            }
            System.out.println(stringResponse);
            JSONObject json = new JSONObject(stringResponse.toString());

            return json.getString("refresh_token");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private String getAccessToken(){
        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpPost post = new HttpPost("https://accounts.spotify.com/api/token");

        post.addHeader(basicAuth());

        List<NameValuePair> nvps = new ArrayList<>(2);
        nvps.add(new BasicNameValuePair("grant_type", "refresh_token"));
        nvps.add(new BasicNameValuePair("refresh_token", Config.REFRESH_TOKEN.toString()));

        try {
            post.setEntity(new UrlEncodedFormEntity(nvps));

            CloseableHttpResponse response = httpClient.execute(post);

            InputStreamReader isr = new InputStreamReader(response.getEntity().getContent());

            StringBuilder stringResponse = new StringBuilder();
            while(isr.ready()){
                stringResponse.append((char) isr.read());
            }
            JSONObject json = new JSONObject(stringResponse.toString());

            return json.getString("access_token");
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    Header bearerAuth(){
        return new BasicHeader("Authorization", "Bearer " + getAccessToken());
    }

    Header basicAuth(){
        return new BasicHeader("Authorization","Basic " +
                Base64.getEncoder().encodeToString((Config.CLIENT_ID.toString() + ":" + Config.CLIENT_SECRET.toString()).getBytes()));
    }
}
