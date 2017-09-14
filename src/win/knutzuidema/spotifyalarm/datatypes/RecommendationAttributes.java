package win.knutzuidema.spotifyalarm.datatypes;

public class RecommendationAttributes {
    private float acousticness;
    private float danceability;
    private int duration_ms;
    private float energy;
    private float instrumentalness;
    private int key;
    private float liveness;
    private float loudness;
    private int mode;
    private int popularity;
    private float speechiness;
    private float tempo;
    private int time_signature;
    private float valence;

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
