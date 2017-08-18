package univ.smu.w9.colink.guiComponent;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import com.jcraft.jsch.ChannelSftp;

/**
 * File tree
 * @author "SukHwanYoon"
 *
 */
public class MyFileJTree implements TreeSelectionListener{

    /**
     * Jtree
     */
    private JTree jTree;

    /**
     * 스크롤판넬
     */
    private JScrollPane jScroll;

    /**
     * 루트(최상위) 트리 노드
     */
    private DefaultMutableTreeNode root;

    /**
     * jTreeModel
     */
    private DefaultTreeModel jTreeModel;

    /**
     * Server : true , local : false
     */
    private boolean ServerYn;

    /**
     * jTree 초기화
     */
    public MyFileJTree() {
        root = new DefaultMutableTreeNode();
        jTree = new JTree(root);
        jTreeModel = (DefaultTreeModel) jTree.getModel();
        jScroll = new JScrollPane(jTree);
    }

    /**
     * jTree 초기화
     * @param rootName
     */
    public MyFileJTree(String rootName){


        root = new DefaultMutableTreeNode();
        makeTreeModel(rootName,root);

        jTree = new JTree(root);
        jTreeModel = (DefaultTreeModel) jTree.getModel();
        jTree.addTreeSelectionListener(this);

        jScroll = new JScrollPane(jTree);

    }

    /**
     * JTree 재 초기화
     * @param rootName
     * @param files
     */
    public void initJTree(String rootName){
        root = new DefaultMutableTreeNode();
        makeTreeModel(rootName,root);
        jTreeModel.setRoot(root);
    }

    /**
     * Recursive Make Tree Model
     * @param rootName
     * @param depth
     */
    public void makeTreeModel(String rootName,DefaultMutableTreeNode root){
        //경로의 전체 파일 검색
        File rootFile = new File(rootName);
        File[] files = rootFile.listFiles();
        DefaultMutableTreeNode dmtBuf;
        for(int i=0;i<files.length;i++){
             if(files[i].isDirectory()){
                 continue;
             }
            dmtBuf = new DefaultMutableTreeNode(files[i].getPath());

            root.add(dmtBuf);
        }
    }

    /**
     * 벡터로 초기화
     * @param rootName
     * @param vector
     */
    public void setByVector(String rootName,Vector<ChannelSftp.LsEntry> vector){
        root = new DefaultMutableTreeNode(rootName);
        Iterator<ChannelSftp.LsEntry> iterator = vector.iterator();
        while(iterator.hasNext()){
            root.add(new DefaultMutableTreeNode(rootName+"/"+iterator.next().getFilename()));
        }
        jTreeModel.setRoot(root);
        jTree.updateUI();
    }

    /**
     * JScrollPane return
     */
    public JScrollPane getjScroll() {
        return jScroll;
    }

    /*
     * Tree Selection Listener
     * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
     */
    public void valueChanged(TreeSelectionEvent arg0) {
        // TODO Auto-generated method stub

    }


    public void setServerYn(boolean serverYn) {
        ServerYn = serverYn;
    }



}
