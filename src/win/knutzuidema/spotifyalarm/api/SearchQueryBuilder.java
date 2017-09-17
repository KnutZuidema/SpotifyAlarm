package win.knutzuidema.spotifyalarm.api;

public class SearchQueryBuilder {
    private static SearchQueryBuilder instance;
    private StringBuilder keywords;
    private StringBuilder exclusions;
    private StringBuilder alternatives;
    private String album;
    private String artist;
    private String track;
    private String year;
    private String genre;
    private boolean isNew = false;
    private boolean isHipster = false;

    public static SearchQueryBuilder getInstance(){
        if(instance == null){
            instance = new SearchQueryBuilder();
        }
        return instance;
    }

    private SearchQueryBuilder(){
        this.keywords = new StringBuilder();
        this.exclusions = new StringBuilder();
        this.alternatives = new StringBuilder();
    }

    public SearchQueryBuilder addKeywords(String keywords){
        this.keywords.append(keywords.trim());
        return this;
    }

    public SearchQueryBuilder addExclusions(String exclusions){
        this.exclusions.append(" NOT ").append(exclusions.trim());
        return this;
    }

    public SearchQueryBuilder addAlternatives(String alternatives){
        this.alternatives.append(alternatives.trim());
        return this;
    }

    public SearchQueryBuilder setAlbum(String album){
        this.album = " album:" + album.trim();
        return this;
    }

    public SearchQueryBuilder setArtist(String artist){
        this.artist = " artist:" + artist.trim();
        return this;
    }

    public SearchQueryBuilder setTrack(String track){
        this.track = " track:" + track.trim();
        return this;
    }

    public SearchQueryBuilder setYear(String year){
        this.year = " year:" + year.trim();
        return this;
    }

    public SearchQueryBuilder setYear(int year){
        return setYear(String.valueOf(year));
    }

    public SearchQueryBuilder setYear(String from, String to){
        this.year = " year:" + from.trim() + "-" + to.trim();
        return this;
    }

    public SearchQueryBuilder setYear(int from, int to){
        return setYear(String.valueOf(from), String.valueOf(to));
    }

    public SearchQueryBuilder setNew(boolean bool){
        this.isNew = bool;
        return this;
    }

    public SearchQueryBuilder setHipster(boolean bool){
        this.isHipster = bool;
        return this;
    }

    public SearchQueryBuilder setFieldGenre(String genre){
        this.genre = "genre:" + genre.trim();
        return this;
    }

    public String build(){
        String keywords = this.keywords.toString();
        String alternatives = this.alternatives.toString();
        String exclusions = this.exclusions.toString();
        this.keywords = new StringBuilder();
        this.alternatives = new StringBuilder();
        this.exclusions = new StringBuilder();

        return keywords +
                " OR " +
                alternatives +
                exclusions +
                (album == null ? "" : album) +
                (artist == null ? "" : artist) +
                (track == null ? "" : track) +
                (year == null ? "" : year) +
                (genre == null ? "" : genre) +
                (isHipster ? " tag:hipster" : "") +
                (isNew ? " tag:new" : "");
    }
}
