package win.knutzuidema.spotifyalarm.api;

import win.knutzuidema.spotifyalarm.datatypes.Artist;
import win.knutzuidema.spotifyalarm.datatypes.RecommendationAttributes;
import win.knutzuidema.spotifyalarm.datatypes.Track;
import win.knutzuidema.spotifyalarm.enums.Genre;

import java.util.LinkedList;
import java.util.List;

public class RecommendationRequestBuilder {
    private int limit;
    private String market;
    private RecommendationAttributes max;
    private RecommendationAttributes min;
    private RecommendationAttributes target;
    private List<Track> trackSeeds;
    private List<Artist> artistSeeds;
    private List<Genre> genreSeeds;
    private int seeds = 0;
    private boolean isFirst = false;

    private RecommendationRequestBuilder(){
        this.max = new RecommendationAttributes();
        this.min = new RecommendationAttributes();
        this.target = new RecommendationAttributes();
        this.trackSeeds = new LinkedList<>();
        this.artistSeeds = new LinkedList<>();
        this.genreSeeds = new LinkedList<>();
    }

    public static RecommendationRequestBuilder create(){
        return new RecommendationRequestBuilder();
    }

    public String build(){
        StringBuilder tracks = new StringBuilder();
        StringBuilder artists = new StringBuilder();
        StringBuilder genres = new StringBuilder();
        for(int i = 0; i < trackSeeds.size(); i++){
            tracks.append(trackSeeds.get(i).getId());
            if(i < trackSeeds.size() - 1){
                tracks.append(',');
            }
        }
        for(int i = 0; i < artistSeeds.size(); i++){
            artists.append(artistSeeds.get(i).getId());
            if(i < artistSeeds.size() - 1){
                artists.append(',');
            }
        }
        for(int i = 0; i < genreSeeds.size(); i++){
            genres.append(genreSeeds.get(i).toString());
            if(i < genreSeeds.size() - 1){
                genres.append(',');
            }
        }

        return (limit == 0 ? "" : pre() + "limit=" + limit) +
                (market == null ? "" : pre() + "market=" + market) +
                (trackSeeds.isEmpty() ? "" : pre() + "seed_tracks=" + tracks) +
                (artistSeeds.isEmpty() ? "" : pre() + "seed_artists=" + artists) +
                (genreSeeds.isEmpty() ? "" : pre() + "seed_genres=" + genres) +
                max.toURIQuery("max", false) +
                min.toURIQuery("min", false) +
                target.toURIQuery("target", false);
    }

    public RecommendationRequestBuilder isFirst(boolean isFirst){
        this.isFirst = isFirst;
        return this;
    }

    public RecommendationRequestBuilder setLimit(int limit){
        assert limit > 0 && limit < 101 : "limit out of bounds";
        this.limit = limit;
        return this;
    }

    public RecommendationRequestBuilder setMarket(String market){
        this.market = market;
        return this;
    }

    public RecommendationRequestBuilder addSeed(Genre seed){
        assert seeds < 6 : "too many seeds";
        this.genreSeeds.add(seed);
        this.seeds++;
        return this;
    }

    public RecommendationRequestBuilder addSeed(Artist seed){
        assert seeds < 6 : "too many seeds";
        this.artistSeeds.add(seed);
        this.seeds++;
        return this;
    }

    public RecommendationRequestBuilder addSeed(Track seed){
        assert seeds < 6 : "too many seeds";
        this.trackSeeds.add(seed);
        this.seeds++;
        return this;
    }

    public RecommendationRequestBuilder setMaxAcousticness(float acousticness) {
        this.max.setAcousticness(acousticness);
        return this;
    }

    public RecommendationRequestBuilder setMaxDanceability(float danceability) {
        this.max.setDanceability(danceability);
        return this;
    }

    public RecommendationRequestBuilder setMaxDuration_ms(int duration_ms) {
        this.max.setDuration_ms(duration_ms);
        return this;
    }

    public RecommendationRequestBuilder setMaxEnergy(float energy) {
        this.max.setEnergy(energy);
        return this;
    }

    public RecommendationRequestBuilder setMaxInstrumentalness(float instrumentalness) {
        this.max.setInstrumentalness(instrumentalness);
        return this;
    }

    public RecommendationRequestBuilder setMaxKey(int key) {
        this.max.setKey(key);
        return this;
    }

    public RecommendationRequestBuilder setMaxLiveness(float liveness) {
        this.max.setLiveness(liveness);
        return this;
    }

    public RecommendationRequestBuilder setMaxLoudness(float loudness) {
        this.max.setLoudness(loudness);
        return this;
    }

    public RecommendationRequestBuilder setMaxMode(int mode) {
        this.max.setMode(mode);
        return this;
    }

    public RecommendationRequestBuilder setMaxPopularity(int popularity) {
        this.max.setPopularity(popularity);
        return this;
    }

    public RecommendationRequestBuilder setMaxSpeechiness(float speechiness) {
        this.max.setSpeechiness(speechiness);
        return this;
    }

    public RecommendationRequestBuilder setMaxTempo(float tempo) {
        this.max.setTempo(tempo);
        return this;
    }

    public RecommendationRequestBuilder setMaxTime_signature(int time_signature) {
        this.max.setTime_signature(time_signature);
        return this;
    }

    public RecommendationRequestBuilder setMaxValence(float valence) {
        this.max.setValence(valence);
        return this;
    }

    public RecommendationRequestBuilder setMinAcousticness(float acousticness) {
        this.min.setAcousticness(acousticness);
        return this;
    }

    public RecommendationRequestBuilder setMinDanceability(float danceability) {
        this.min.setDanceability(danceability);
        return this;
    }

    public RecommendationRequestBuilder setMinDuration_ms(int duration_ms) {
        this.min.setDuration_ms(duration_ms);
        return this;
    }

    public RecommendationRequestBuilder setMinEnergy(float energy) {
        this.min.setEnergy(energy);
        return this;
    }

    public RecommendationRequestBuilder setMinInstrumentalness(float instrumentalness) {
        this.min.setInstrumentalness(instrumentalness);
        return this;
    }

    public RecommendationRequestBuilder setMinKey(int key) {
        this.min.setKey(key);
        return this;
    }

    public RecommendationRequestBuilder setMinLiveness(float liveness) {
        this.min.setLiveness(liveness);
        return this;
    }

    public RecommendationRequestBuilder setMinLoudness(float loudness) {
        this.min.setLoudness(loudness);
        return this;
    }

    public RecommendationRequestBuilder setMinMode(int mode) {
        this.min.setMode(mode);
        return this;
    }

    public RecommendationRequestBuilder setMinPopularity(int popularity) {
        this.min.setPopularity(popularity);
        return this;
    }

    public RecommendationRequestBuilder setMinSpeechiness(float speechiness) {
        this.min.setSpeechiness(speechiness);
        return this;
    }

    public RecommendationRequestBuilder setMinTempo(float tempo) {
        this.min.setTempo(tempo);
        return this;
    }

    public RecommendationRequestBuilder setMinTime_signature(int time_signature) {
        this.min.setTime_signature(time_signature);
        return this;
    }

    public RecommendationRequestBuilder setMinValence(float valence) {
        this.min.setValence(valence);
        return this;
    }

    public RecommendationRequestBuilder setTargetAcousticness(float acousticness) {
        this.target.setAcousticness(acousticness);
        return this;
    }

    public RecommendationRequestBuilder setTargetDanceability(float danceability) {
        this.target.setDanceability(danceability);
        return this;
    }

    public RecommendationRequestBuilder setTargetDuration_ms(int duration_ms) {
        this.target.setDuration_ms(duration_ms);
        return this;
    }

    public RecommendationRequestBuilder setTargetEnergy(float energy) {
        this.target.setEnergy(energy);
        return this;
    }

    public RecommendationRequestBuilder setTargetInstrumentalness(float instrumentalness) {
        this.target.setInstrumentalness(instrumentalness);
        return this;
    }

    public RecommendationRequestBuilder setTargetKey(int key) {
        this.target.setKey(key);
        return this;
    }

    public RecommendationRequestBuilder setTargetLiveness(float liveness) {
        this.target.setLiveness(liveness);
        return this;
    }

    public RecommendationRequestBuilder setTargetLoudness(float loudness) {
        this.target.setLoudness(loudness);
        return this;
    }

    public RecommendationRequestBuilder setTargetMode(int mode) {
        this.target.setMode(mode);
        return this;
    }

    public RecommendationRequestBuilder setTargetPopularity(int popularity) {
        this.target.setPopularity(popularity);
        return this;
    }

    public RecommendationRequestBuilder setTargetSpeechiness(float speechiness) {
        this.target.setSpeechiness(speechiness);
        return this;
    }

    public RecommendationRequestBuilder setTargetTempo(float tempo) {
        this.target.setTempo(tempo);
        return this;
    }

    public RecommendationRequestBuilder setTargetTime_signature(int time_signature) {
        this.target.setTime_signature(time_signature);
        return this;
    }

    public RecommendationRequestBuilder setTargetValence(float valence) {
        this.target.setValence(valence);
        return this;
    }

    private String pre(){
        if(!this.isFirst){
            this.isFirst = false;
            return "?";
        }
        return "&";
    }
}
