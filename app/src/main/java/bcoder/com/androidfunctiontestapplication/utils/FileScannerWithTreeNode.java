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

public class FileScannerWithTreeNode {
    public static final String LOG_TAG = "FileScannerWithTreeNode";

    private boolean scanning = false;
    private final MusicFileFilter mMusicFilter = new MusicFileFilter();

    private FileTreeNode mFileTreeNode;

    public void scanFiles(String path){
        File file = new File(path);
        mFileTreeNode = new FileTreeNode(file.getName(), null);

        Stack<FileTreeNodeForStack> folderStack = new Stack<>();
        FileTreeNodeForStack tmpFileForStack = new FileTreeNodeForStack(file, mFileTreeNode);
        folderStack.push(tmpFileForStack);

        scanning = true;

        long starttime = System.currentTimeMillis();

        while (!folderStack.empty()){
            if(!scanning){
                break;
            }
            tmpFileForStack = folderStack.pop();
            FileTreeNode tmpTreeNode = tmpFileForStack.getFileTreeNode();
            file = tmpFileForStack.getFile();

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
                    FileTreeNode tmpDirNode = tmpTreeNode.addChild(files[i].getName(), files[i]);
                    tmpDirNode.setDirectory(true);
                    tmpDirNode.setFileName(files[i].getName());
                    tmpDirNode.setFullPath(files[i].getAbsolutePath());

                    folderStack.push(new FileTreeNodeForStack(files[i], tmpDirNode));
                } else {
                    FileTreeNode tmpfilenode = tmpTreeNode.addChild(files[i].getName(), files[i]);
//                    Log.d(LOG_TAG, "set hasMusic: " + files[i].getAbsolutePath());
                    tmpfilenode.setHasMusic(true);
                    tmpfilenode.setFileName(files[i].getName());
                    tmpfilenode.setFullPath(files[i].getAbsolutePath());
                }
            }
        }

        long timeuse = System.currentTimeMillis() - starttime;

        Log.d(LOG_TAG, String.format("TimeUse: %d", timeuse));

        HashMap<String, ArrayList<String>> fileList = new HashMap<>();

        //　打印获取到的目录和文件结构
        FileTreeNode outNode = mFileTreeNode;
        Stack<FileTreeNode> stackNodes = new Stack<>();
        stackNodes.push(outNode);
        while (!stackNodes.empty()){
            outNode = stackNodes.pop();
            String fullpath = outNode.getFullPath();
            ArrayList<String> innerfilelist = new ArrayList<>();
            for(int i = 0; i < outNode.getChildren().size(); i++){
                if(outNode.getChildren().get(i).isDirectory() && outNode.getChildren().get(i).isHasMusic()){
                    stackNodes.push(outNode.getChildren().get(i));

//                    Log.d(LOG_TAG, String.format("outnode dir: %s", outNode.getChildren().get(i).getFileName()));
                } else {
//                    Log.d(LOG_TAG, String.format("outnode file: %s", outNode.getChildren().get(i).getFileName()));
                }
                innerfilelist.add(outNode.getChildren().get(i).getFileName());
            }

            fileList.put(fullpath, innerfilelist);
        }


//        for(HashMap.Entry<String, ArrayList<String>> entry: fileList.entrySet()){
//            Log.d(LOG_TAG, entry.getKey());
//        }

        Log.d(LOG_TAG, String.format("filelist size: %d", fileList.size()));
    }

    public void stopScan(){
        scanning = false;
    }
}
