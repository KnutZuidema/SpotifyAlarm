package win.knutzuidema.spotifyalarm.api;

import org.apache.http.client.methods.HttpUriRequest;
import win.knutzuidema.spotifyalarm.datatypes.User;

public class UserAPI {

    public static User getUser(String id){
        HttpUriRequest request = API
                .requestBuilder("GET", "/users/" + id)
                .build();

        return new User(API.getJSON(request));
    }
}
