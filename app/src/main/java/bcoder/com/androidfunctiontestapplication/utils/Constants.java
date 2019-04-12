package bcoder.com.androidfunctiontestapplication.utils;

import java.util.HashSet;

public class Constants {
    public static final String MUSIC_FILE_EXT_STRING = ".mp3,.wav";
    public static final HashSet<String> MUSIC_FILE_EXTS = new HashSet<>();

    public static void initialize() {
        String[] musicexts = MUSIC_FILE_EXT_STRING.split(",");
        for(int i = 0; i < musicexts.length; i++){
            MUSIC_FILE_EXTS.add(musicexts[i]);
        }
    }


}
