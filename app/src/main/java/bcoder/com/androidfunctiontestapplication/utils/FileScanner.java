package bcoder.com.androidfunctiontestapplication.utils;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;

public class FileScanner {
    public static final String LOG_TAG = "FileScanner";
    public ArrayList<String> mFileList = new ArrayList<>();

    private boolean mIsRunning = true;

    public int scanFiles(String path){
        mFileList.clear();
        File file = new File(path);
        Stack<File> folderStack = new Stack<>();
        folderStack.push(file);

        long starttime = System.currentTimeMillis();

        while (!folderStack.empty()){
            if(!mIsRunning){
                break;
            }
            file = folderStack.pop();
            File[] files = file.listFiles();

            //　内部排序
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                }
            });

            for(int i = 0; i < files.length;  i++){
                String fullfilepath = files[i].getAbsolutePath();

                if(files[i].isDirectory() && !"./".equals(files[i].getName())){
                    folderStack.push(files[i]);
                    mFileList.add(fullfilepath);
                } else {
                    mFileList.add(fullfilepath);
                }
            }
        }

        long timeuse = System.currentTimeMillis() - starttime;

        Log.d(LOG_TAG, String.format("TimeUse: %d", timeuse));

        Arrays.sort(mFileList.toArray());

        //　外部排序
        mFileList.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        Log.d(LOG_TAG, String.format("File count: %d", mFileList.size()));

//        for(int i = 0; i < mFileList.size(); i++){
//            Log.d(LOG_TAG, mFileList.get(i));
//        }

        return mFileList.size();
    }

    public void stopScan(){
        mIsRunning = false;
    }
}
