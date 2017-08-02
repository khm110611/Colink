package univ.smu.w9.colink.guiComponent;

import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 폴더 Tree
 * @author "SukHwanYoon"
 *
 */
public class MyFolderJTree implements TreeSelectionListener{

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
     * 파일트리
     */
    private MyFileJTree fileJTree;

    /**
     * jTree 초기화
     */
    public MyFolderJTree() {
        root = new DefaultMutableTreeNode();
        jTree = new JTree(root);
        jScroll = new JScrollPane(jTree);
    }

    /**
     * jTree 초기화
     * @param rootName
     */
    public MyFolderJTree(String rootName,MyFileJTree fileJTree){

        this.fileJTree = fileJTree;

        root = new DefaultMutableTreeNode(rootName);
        makeTreeModel(rootName,root);

        jTree = new JTree(root);
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

        jTree.updateUI();
    }

    /**
     * Make Tree Model
     * @param rootName
     */
    public void makeTreeModel(String rootName,DefaultMutableTreeNode root){
        makeTreeModel(rootName,root,0);
    }

    /**
     * Recursive Make Tree Model
     * @param rootName
     * @param depth
     */
    public void makeTreeModel(String rootName,DefaultMutableTreeNode root,int depth){
        if(depth == 5){
            root = new DefaultMutableTreeNode("");
            return;
        }
        int idx = 0;
        //경로의 전체 파일 검색
        File rootFile = new File(rootName);
        File[] files = rootFile.listFiles();
        DefaultMutableTreeNode dmtBuf;
        // 파일이 없을경우
        if(files == null){
            return;
        }

        for(int i=0;i<files.length;i++){
            if(files[i].isFile()){
                continue;
            }

            dmtBuf = new DefaultMutableTreeNode(files[i].getPath());

            makeTreeModel(files[i].getPath(),dmtBuf,depth+1);

            root.add(dmtBuf);
        }
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
    public void valueChanged(TreeSelectionEvent e) {
        if(jTree.getLastSelectedPathComponent() != null){
            File file = new File(jTree.getLastSelectedPathComponent().toString());
            fileJTree.initJTree(file.getPath());
        }
    }


}
