
public class MusicBox {
    
    private String songID;
    private char isSongPremium;
    private String songTitle;
    private String songArtists;
    private String songAlbum;
    private String songGenre;
    private String songProducer;
    private String songMusicLabel;
    private int noAds;

    
    public MusicBox(String songID, char isSongPremium, String songTitle,
                    String songArtists, String songAlbum, String songGenre,
                    String songProducer, String songMusicLabel, int noAds) {
        this.songID = songID;
        this.isSongPremium = isSongPremium;
        this.songTitle = songTitle;
        this.songArtists = songArtists;
        this.songAlbum = songAlbum;
        this.songGenre = songGenre;
        this.songProducer = songProducer;
        this.songMusicLabel = songMusicLabel;
        this.noAds = noAds;
    }

    
    public String getSongID() {
        return songID;
    }

    public char getIsSongPremium() {
        return isSongPremium;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public String getSongArtists() {
        return songArtists;
    }

    public String getSongAlbum() {
        return songAlbum;
    }

    public String getSongGenre() {
        return songGenre;
    }

    public String getSongProducer() {
        return songProducer;
    }

    public String getSongMusicLabel() {
        return songMusicLabel;
    }

    public int getNoAds() {
        return noAds;
    }
}