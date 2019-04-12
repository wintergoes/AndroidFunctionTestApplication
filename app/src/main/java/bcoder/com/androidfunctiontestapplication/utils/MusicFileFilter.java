package bcoder.com.androidfunctiontestapplication.utils;

import java.io.File;
import java.io.FileFilter;

public class MusicFileFilter implements FileFilter {

    @Override
    public boolean accept(File pathname) {
//        return true;


        if(pathname.isDirectory()){
            return true;
        }

        int dotIndex = pathname.getName().lastIndexOf(".");
        if(dotIndex < 0){
            return false;
        }

        String fileext = pathname.getName().substring(dotIndex).toLowerCase();
        if(Constants.MUSIC_FILE_EXTS.contains(fileext)){
            return true;
        }

        return false;
    }
}
