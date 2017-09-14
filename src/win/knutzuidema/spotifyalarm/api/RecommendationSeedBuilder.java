package win.knutzuidema.spotifyalarm.api;

import win.knutzuidema.spotifyalarm.datatypes.Artist;
import win.knutzuidema.spotifyalarm.datatypes.RecommendationAttributes;
import win.knutzuidema.spotifyalarm.datatypes.Track;

import java.util.List;

public class RecommendationSeedBuilder {
    private static RecommendationSeedBuilder instance;

    private int limit;
    private String market;
    private RecommendationAttributes max;
    private RecommendationAttributes min;
    private RecommendationAttributes target;
    private List<Track> trackSeeds;
    private List<Artist> artistSeeds;
    private List<String> genreSeeds;

    private RecommendationSeedBuilder(){}

    public static RecommendationSeedBuilder getInstance(){
        if(instance == null){
            instance = new RecommendationSeedBuilder();
        }
        return instance;
    }
}
