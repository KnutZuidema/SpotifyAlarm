package win.knutzuidema.spotifyalarm.datatypes;

public class RecommendationAttributes {
    private Float acousticness;
    private Float danceability;
    private Integer duration_ms;
    private Float energy;
    private Float instrumentalness;
    private Integer key;
    private Float liveness;
    private Float loudness;
    private Integer mode;
    private Integer popularity;
    private Float speechiness;
    private Float tempo;
    private Integer time_signature;
    private Float valence;
    private boolean usedFirst = false;

    public String toURIQuery(String prefix, boolean isFirst){
        return (this.acousticness == null ? "" : pre(isFirst) + prefix + "_acousticness=" + this.acousticness) +
                (this.danceability == null ? "" : pre(isFirst) + prefix + "_danceability=" + this.danceability) +
                (this.duration_ms == null ? "" : pre(isFirst) + prefix + "_duration_ms=" + this.duration_ms) +
                (this.energy == null ? "" : pre(isFirst) + prefix + "_energy=" + this.energy) +
                (this.instrumentalness == null ? "" : pre(isFirst) + prefix + "_instrumentalness=" + this.instrumentalness) +
                (this.key == null ? "" : pre(isFirst) + prefix + "_key=" + this.key) +
                (this.liveness == null ? "" : pre(isFirst) + prefix + "_liveness=" + this.liveness) +
                (this.loudness == null ? "" : pre(isFirst) + prefix + "_loudness=" + this.loudness) +
                (this.mode == null ? "" : pre(isFirst) + prefix + "_mode=" + this.mode) +
                (this.popularity == null ? "" : pre(isFirst) + prefix + "_popularity=" + this.popularity) +
                (this.speechiness == null ? "" : pre(isFirst) + prefix + "_speechiness=" + this.speechiness) +
                (this.tempo == null ? "" : pre(isFirst) + prefix + "_tempo=" + this.tempo) +
                (this.time_signature == null ? "" : pre(isFirst) + prefix + "_time_signature=" + this.time_signature) +
                (this.valence == null ? "" : pre(isFirst) + prefix + "_valence=" + this.valence);
    }

    private String pre(boolean arg){
        if(arg){
            if(!this.usedFirst){
                this.usedFirst = true;
                return "?";
            }
        }
        return "&";
    }

    public float getAcousticness() {
        return acousticness;
    }

    public void setAcousticness(float acousticness) {
        if(acousticness > 1.0 || acousticness < 0.0){
            throw new IllegalArgumentException("value needs to be between 0.0 and 1.0");
        }
        this.acousticness = acousticness;
    }

    public float getDanceability() {
        return danceability;
    }

    public void setDanceability(float danceability) {
        if(danceability > 1.0 || danceability < 0.0){
            throw new IllegalArgumentException("value needs to be between 0.0 and 1.0");
        }
        this.danceability = danceability;
    }

    public int getDuration_ms() {
        return duration_ms;
    }

    public void setDuration_ms(int duration_ms) {
        this.duration_ms = duration_ms;
    }

    public float getEnergy() {
        return energy;
    }

    public void setEnergy(float energy) {
        if(energy > 1.0 || energy < 0.0){
            throw new IllegalArgumentException("value needs to be between 0.0 and 1.0");
        }
        this.energy = energy;
    }

    public float getInstrumentalness() {
        return instrumentalness;
    }

    public void setInstrumentalness(float instrumentalness) {
        if(instrumentalness > 1.0 || instrumentalness < 0.0){
            throw new IllegalArgumentException("value needs to be between 0.0 and 1.0");
        }
        this.instrumentalness = instrumentalness;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public float getLiveness() {
        return liveness;
    }

    public void setLiveness(float liveness) {
        if(liveness > 1.0 || liveness < 0.0){
            throw new IllegalArgumentException("value needs to be between 0.0 and 1.0");
        }
        this.liveness = liveness;
    }

    public float getLoudness() {
        return loudness;
    }

    public void setLoudness(float loudness) {
        this.loudness = loudness;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public float getSpeechiness() {
        return speechiness;
    }

    public void setSpeechiness(float speechiness) {
        if(speechiness > 1.0 || speechiness < 0.0){
            throw new IllegalArgumentException("value needs to be between 0.0 and 1.0");
        }
        this.speechiness = speechiness;
    }

    public float getTempo() {
        return tempo;
    }

    public void setTempo(float tempo) {
        this.tempo = tempo;
    }

    public int getTime_signature() {
        return time_signature;
    }

    public void setTime_signature(int time_signature) {
        this.time_signature = time_signature;
    }

    public float getValence() {
        return valence;
    }

    public void setValence(float valence) {
        if(valence > 1.0 || valence < 0.0){
            throw new IllegalArgumentException("value needs to be between 0.0 and 1.0");
        }
        this.valence = valence;
    }
}
