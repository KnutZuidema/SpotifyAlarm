package win.knutzuidema.spotifyalarm;

import org.apache.http.client.utils.URIBuilder;
import win.knutzuidema.spotifyalarm.enums.Config;

import java.awt.*;
import java.net.URI;

public class Main {
    public static void main(String[] args){
        /*URIBuilder builder = new URIBuilder();
        Desktop desktop = Desktop.getDesktop();
        try {
            URI authorize = builder.setScheme("https")
                    .setHost("accounts.spotify.com")
                    .setPath("/authorize/")
                    .addParameter("client_id", Config.CLIENT_ID.toString())
                    .addParameter("response_type", "code")
                    .addParameter("redirect_uri", Config.REDIRECT_URI.toString())
                    .addParameter("scope", "streaming playlist-read-private playlist-read-collaborative playlist-modify-private playlist-modify-public user-library-read user-library-modify user-read-playback-state user-modify-playback-state user-read-currently-playing user-read-recently-played")
                    .build();

            desktop.browse(authorize);
        }catch(Exception e){
            e.printStackTrace();
        }*/

        Auth auth = new Auth();
        System.out.println(auth.getAccessToken());
    }
}
