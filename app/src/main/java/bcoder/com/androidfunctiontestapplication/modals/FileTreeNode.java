package bcoder.com.androidfunctiontestapplication.modals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTreeNode {
    private static final String LOG_TAG = "FileTreeNode";

    private FileTreeNode mParentNode = null;
    private List<FileTreeNode> children = new ArrayList<>();

    private boolean directory = false;
    private boolean hasMusic = false;
    private String mFileName;
    private String mFullPath;

    public FileTreeNode(FileTreeNode fileTreeNode) {
        this.mParentNode = fileTreeNode.mParentNode;
        this.mFileName = fileTreeNode.mFileName;
        this.directory = fileTreeNode.isDirectory();
    }

    public List<FileTreeNode> getChildren() {
        return children;
    }

    public boolean isDirectory() {
        return directory;
    }

    public void setDirectory(boolean directory) {
        this.directory = directory;
    }

    public FileTreeNode(String filename, FileTreeNode parent) {
        mFileName = filename;
        mParentNode = parent;
    }

    public FileTreeNode addChild(String filename, File file){
        FileTreeNode child = new FileTreeNode(filename, this);
        children.add(child);
        return child;
    }

    public FileTreeNode getParentNode() {
        return mParentNode;
    }

    public String getFileName() {
        return mFileName;
    }

    public void setFileName(String mFileName) {
        this.mFileName = mFileName;
    }

    public String getFullPath() {
        return mFullPath;
    }

    public void setFullPath(String fullPath) {
        mFullPath = fullPath;
    }

    @Override
    public String toString() {
        return mFileName.toString();
    }

    public boolean isHasMusic() {
        return hasMusic;
    }

    public void setHasMusic(boolean hasMusic) {
        this.hasMusic = hasMusic;
        FileTreeNode tmpNode = this.mParentNode;
        while (tmpNode != null){
            if(tmpNode.isHasMusic()){
                break;
            }
            tmpNode.hasMusic = hasMusic;
//            Log.d(LOG_TAG, "setHasMusic: " + tmpNode.mFileName);
            tmpNode = tmpNode.mParentNode;
        }
    }
}
