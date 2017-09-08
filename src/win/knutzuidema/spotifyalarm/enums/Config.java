package win.knutzuidema.spotifyalarm.enums;

public enum Config {
    ACCESS_CODE("AQAY5j-XewDuse54WsjpwAO57BKBbUoEmOLiYUqnksP6AY4vquDRm4gcA2ulF0KD2t_s3J7ABSlWIVDd6wAQOUCCJXAMo4IgkIOX_KXTklMEvUD_XjFg7SVL9s0lTLwg_LmFMlQOY8ewsKEHkTD3jehSHrrdcv1ZjUvTU8FciXEowre0YVyJqh9ibxXGRbqs1wqC9HHMLv4aDk7eOMCQZVFs7um6wgscBeOZdzLzl8VFt4JdKzgiiM8f8E2RT3k1Q0sWzjrELispAGsXkIB4Of_NZn_v0sqKk3B1jKqMcVsvRHmc1PTjLd3pFq0iEZ5rB-JUmqD39Epz1dD--lT61CdhhJeEpoZQhUCTBsfyjBRsXIH6hWmRX14MrbSjm7FrnXpaQTcTu89fJC84yn2MwGilDKDWWNJX3RWuZbbYRc8uMp4c4gffRDkmH8qXnspqGX6v0lwhXUVsgFUSKAQe-_dSiIebEXZGy6s6L80A4wrKHj4npzkEK60vAinLPseizAPJmfng5sg0JeVTL-Z8Nm911g"),
    REFRESH_TOKEN("AQAZ3uDaj3tTbZJixdLbbjr2ZJbtL4tnG69r6R18qszcXH64xmZBqwMw8fQbzIoX_aBVYFs7HO00LNpE5JU1CPWepRpnJpNG3nzGd91r10Lye39JPUP3l32WI3hpYtz_WV8"),
    USER_AGENT("win.knutzuidema.spotifyalarm//v0.1: SpotifyAlarm (by Knut Zuidema)"),
    CLIENT_ID("93914c11455b48129810b4d273d4dbe6"),
    CLIENT_SECRET("989a20d3c73540919bac06a856564948"),
    REDIRECT_URI("https://www.spotify.com"),
    API_URI("https://api.spotify.com/v1");

    private String value;

    Config(String value){
        this.value = value;
    }

    @Override
    public String toString(){
        return this.value;
    }
}
