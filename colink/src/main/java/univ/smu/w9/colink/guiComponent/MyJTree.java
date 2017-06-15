package univ.smu.w9.colink.guiComponent;

import java.io.File;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * 하단 전체 Tree
 * @author "SukHwanYoon"
 *
 */
public class MyJTree implements TreeSelectionListener{

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
     * operator
     * 1 : 폴더트리
     * 2 : 파일트리
     */
    private int operator;


    /**
     * jTree 초기화
     */
    public MyJTree() {
        root = new DefaultMutableTreeNode();
        jTree = new JTree(root);
        jScroll = new JScrollPane(jTree);
    }

    /**
     * jTree 초기화
     * @param rootName
     */
    public MyJTree(String rootName,int operator){

        this.operator = operator;

        root = new DefaultMutableTreeNode();
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
        int idx;
        //경로의 전체 파일 검색
        File rootFile = new File(rootName);
        File[] files = rootFile.listFiles();
        DefaultMutableTreeNode dmtBuf;
        //폴더 트리 일 경우
        if(this.operator == 1){
            for(int i=0;i<files.length;i++){
                if(files[i].isFile()){
                    continue;
                }

                idx = files[i].getPath().indexOf("\\Desktop\\");
                dmtBuf = new DefaultMutableTreeNode(files[i].getPath().substring(idx));

                makeTreeModel(files[i].getPath(),dmtBuf,depth+1);

                root.add(dmtBuf);
            }
       }
       //파일 트리 일 경우
        else if(this.operator == 2){
            for(int i=0;i<files.length;i++){
                if(files[i].isDirectory()){
                    continue;
                }
                idx = files[i].getPath().indexOf("\\Desktop\\");
                dmtBuf = new DefaultMutableTreeNode(files[i].getPath().substring(idx));

                root.add(dmtBuf);
            }
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
        // TODO Auto-generated method stub

    }


}
