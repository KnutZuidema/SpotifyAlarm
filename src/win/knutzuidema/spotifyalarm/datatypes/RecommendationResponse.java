package win.knutzuidema.spotifyalarm.datatypes;

import org.json.JSONArray;
import org.json.JSONObject;
import win.knutzuidema.spotifyalarm.interfaces.Serializer;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class RecommendationResponse implements Serializable, Serializer{
    private static final long serialVersionUID = 0xE00;

    private List<Track> tracks;
    private List<RecommendationSeed> seeds;

    public RecommendationResponse(JSONObject json){
        JSONArray array = json.getJSONArray("tracks");
        List<Track> tracks = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            tracks.add(new Track(array.getJSONObject(i)));
        }
        this.tracks = tracks;

        array = json.getJSONArray("seeds");
        List<RecommendationSeed> seeds = new LinkedList<>();
        for(int i = 0; i < array.length(); i++){
            seeds.add(new RecommendationSeed(array.getJSONObject(i)));
        }
        this.seeds = seeds;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public List<RecommendationSeed> getSeeds() {
        return seeds;
    }
}
