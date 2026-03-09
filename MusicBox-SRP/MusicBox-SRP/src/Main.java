public class Main {
    public static void main(String[] args) {
        
        MusicBox mbox = new MusicBox(
                "S34TG65",        // songID
                'Y',              // isSongPremium
                "Raindrops",      // songTitle
                "Misty",          // songArtists
                "The Path Less Traveled", // songAlbum
                "Country",        // songGenre
                "BZEE Music",     // songProducer
                "Rhythm Divine",  // songMusicLabel
                2                 // noAds
        );

        PlaySongs playsong = new PlaySongs();
        playsong.playSong(mbox.getSongID(), mbox.getIsSongPremium(), mbox.getNoAds());

        // 第二个对象（按要求：premium=N，ads=1）
        MusicBox mbox2 = new MusicBox(
                "S99AB01",
                'N',
                "Sunshine Breeze",
                "Cloudy Skies",
                "Free Vibes",
                "Pop",
                "Indie Sound Studio",
                "Open Music Label",
                1
        );
        playsong.playSong(mbox2.getSongID(), mbox2.getIsSongPremium(), mbox2.getNoAds());
    }
}