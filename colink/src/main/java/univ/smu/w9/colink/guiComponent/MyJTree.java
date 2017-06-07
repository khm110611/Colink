package univ.smu.w9.colink.guiComponent;

import java.io.File;

import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;

public class MyJTree implements TreeSelectionListener{

    private JTree jTree;
    private DefaultMutableTreeNode root;

    /**
     * jTree 초기화
     * @param rootName
     */
    public MyJTree(String rootName,File[] files){
        root  = makeTreeModel(rootName,files);
        jTree = new JTree(root);
        jTree.addTreeSelectionListener(this);
    }

    /**
     * JTree 재 초기화
     * @param rootName
     * @param files
     */
    public void initJTree(String rootName,File[] files){
        root = makeTreeModel(rootName,files);
        jTree.updateUI();
    }

    /**
     * Make Tree Model
     * @param rootName
     * @param files
     * @return
     */
    public DefaultMutableTreeNode makeTreeModel(String rootName,File[] files){
        File fileBuf;
        DefaultMutableTreeNode dmtBuf;
        DefaultMutableTreeNode dmtBuff;
        for(int i=0;i<files.length;i++){
            fileBuf = files[i];

            dmtBuf = new DefaultMutableTreeNode(files[i]);
            //폴더 일경우
            if(fileBuf.isDirectory()){
                dmtBuff = new DefaultMutableTreeNode("");
                dmtBuf.add(dmtBuff);
            }
            root.add(dmtBuf);
        }
        return root;
    }

    /*
     * Tree Selection Listener
     * @see javax.swing.event.TreeSelectionListener#valueChanged(javax.swing.event.TreeSelectionEvent)
     */
    public void valueChanged(TreeSelectionEvent e) {
        // TODO Auto-generated method stub

    }

}
