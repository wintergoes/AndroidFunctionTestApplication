package bcoder.com.androidfunctiontestapplication.utils;

import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Stack;

import bcoder.com.androidfunctiontestapplication.modals.FileTreeNode;
import bcoder.com.androidfunctiontestapplication.modals.FileTreeNodeForStack;

public class FileScannerWithHashMap {
    public static final String LOG_TAG = "FileScannerWithHashMap";

    private boolean scanning = false;
    private final MusicFileFilter mMusicFilter = new MusicFileFilter();

    public void scanFiles(String path){
        File file = new File(path);

        Stack<File> folderStack = new Stack<>();
        folderStack.push(file);

        scanning = true;

        long starttime = System.currentTimeMillis();
        File parentfile;

        HashMap<String, ArrayList<String>> fileList = new HashMap<>();

        while (!folderStack.empty()){
            if(!scanning){
                break;
            }
            file = folderStack.pop();

            File[] files = file.listFiles(mMusicFilter);
            Arrays.sort(files, new Comparator<File>() {
                @Override
                public int compare(File o1, File o2) {
                    return o1.getName().toLowerCase().compareTo(o2.getName().toLowerCase());
                }
            });

            for(int i = 0; i < files.length;  i++){
                if(!scanning){
                    break;
                }

                String fullfilepath = files[i].getAbsolutePath();

                if(files[i].isDirectory() && !"./".equals(files[i].getName())){
                    folderStack.push(files[i]);
                } else {
                    parentfile = files[i].getParentFile();
                    while (parentfile != null){
                        ArrayList<String> tmpList;
                        if (fileList.containsKey(parentfile.getAbsolutePath())){
                            tmpList = fileList.get(parentfile.getAbsolutePath());
                            tmpList.add(files[i].getName());
                        } else {
                            tmpList = new ArrayList<>();
                            fileList.put(parentfile.getAbsolutePath(), tmpList);
                        }

                        parentfile = parentfile.getParentFile();


//                        if(parentfile == null || path.equals(parentfile.getAbsolutePath())){
////                            if(parentfile != null) {
////                                Log.d(LOG_TAG, parentfile.getAbsolutePath());
////                            }
//                            break;
//                        }
                    }
                }
            }
        }

        long timeuse = System.currentTimeMillis() - starttime;

        Log.d(LOG_TAG, String.format("TimeUse: %d", timeuse));


        Log.d(LOG_TAG, String.format("filelist size: %d", fileList.size()));

//        for(HashMap.Entry<String, ArrayList<String>> entry: fileList.entrySet()){
//            Log.d(LOG_TAG, entry.getKey());
//        }
    }

    public void stopScan(){
        scanning = false;
    }
}
