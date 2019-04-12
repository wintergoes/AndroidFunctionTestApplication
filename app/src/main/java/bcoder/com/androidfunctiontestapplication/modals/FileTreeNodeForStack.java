package bcoder.com.androidfunctiontestapplication.modals;

import java.io.File;

public class FileTreeNodeForStack {
    private File file;
    private FileTreeNode fileTreeNode;

    public FileTreeNodeForStack(File file, FileTreeNode fileTreeNode) {
        this.file = file;
        this.fileTreeNode = fileTreeNode;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public FileTreeNode getFileTreeNode() {
        return fileTreeNode;
    }

    public void setFileTreeNode(FileTreeNode fileTreeNode) {
        this.fileTreeNode = fileTreeNode;
    }

    public void setStackBean(File file, FileTreeNode treeNode){
        this.file = file;
        this.fileTreeNode = treeNode;
    }
}
