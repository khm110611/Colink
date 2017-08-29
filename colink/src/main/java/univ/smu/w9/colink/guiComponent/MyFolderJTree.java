package univ.smu.w9.colink.guiComponent;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;

import com.jcraft.jsch.ChannelSftp;

import univ.smu.w9.colink.service.FtpService;

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
    private MyTreeNode root;

    /**
     * 파일트리
     */
    private MyFileJTree fileJTree;

    /**
     * jTreeModel
     */
    private DefaultTreeModel jTreeModel;

    /**
     * Server : true , local : false
     */
    private boolean serverYn;

    private FtpService ftpService;
    
    /**
     * jTree 초기화
     */
    public MyFolderJTree() {
        root = new MyTreeNode();
        jTree = new JTree(root);
        jTree.addTreeSelectionListener(this);
        jTreeModel = (DefaultTreeModel) jTree.getModel();
        jScroll = new JScrollPane(jTree);
    }

    /**
     * jTree 초기화
     * @param rootName
     */
    public MyFolderJTree(String rootName,MyFileJTree fileJTree){

        this.fileJTree = fileJTree;

        root = new MyTreeNode(rootName);
        root.setRealPath(rootName);
        makeTreeModel(rootName,root);

        jTree = new JTree(root);
        jTree.addTreeSelectionListener(this);
        jTreeModel = (DefaultTreeModel) jTree.getModel();
        jScroll = new JScrollPane(jTree);

    }

    /**
     * JTree 재 초기화
     * @param rootName
     * @param files
     */
    public void initJTree(String rootName){
        root = new MyTreeNode();
        makeTreeModel(rootName,root);

        jTree.updateUI();
    }

    /**
     * 벡터로 초기화
     * @param rootName
     * @param vector
     */
    public void setByVector(String rootName,Vector<ChannelSftp.LsEntry> vector){
        root = new MyTreeNode(rootName);
        root.setRealPath(rootName);
        Iterator<ChannelSftp.LsEntry> iterator = vector.iterator();
        MyTreeNode mtnBuf;
        ChannelSftp.LsEntry lsBuf;
        while(iterator.hasNext()){
        	lsBuf = iterator.next();
        	mtnBuf = new MyTreeNode(lsBuf.getFilename());
            mtnBuf.setRealPath(rootName+"/"+lsBuf.getFilename());
            mtnBuf.add(new MyTreeNode(""));
        	root.add(mtnBuf);
        }
        jTreeModel.setRoot(root);
        jTree.updateUI();
    }

    /**
     * Make Tree Model
     * @param rootName
     */
    public void makeTreeModel(String rootName,MyTreeNode root){
        makeTreeModel(rootName,root,0);
    }

    /**
     * Recursive Make Tree Model
     * @param rootName
     * @param depth
     */
    public void makeTreeModel(String rootName,MyTreeNode root,int depth){
        if(depth == 5){
            root = new MyTreeNode("");
            return;
        }
        //경로의 전체 파일 검색
        File rootFile = new File(rootName);
        File[] files = rootFile.listFiles();
        MyTreeNode dmtBuf;
        // 파일이 없을경우
        if(files == null){
        	root.add(new MyTreeNode(""));
        	return;
        }

        for(int i=0;i<files.length;i++){
            if(files[i].isFile() || files[i].isHidden()){
                continue;
            }

            dmtBuf = new MyTreeNode(files[i].getName());
            dmtBuf.setRealPath(files[i].getPath());

            makeTreeModel(files[i].getPath(),dmtBuf,depth+1);

            root.add(dmtBuf);
            
        }
        if(root.getChildCount() == 0){
        	root.add(new MyTreeNode(""));
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
    	if(((MyTreeNode)jTree.getLastSelectedPathComponent()).getRealPath() == null){
    		return;
    	}
        if(serverYn){
            if(jTree.getLastSelectedPathComponent() != null){
            	((MyTreeNode)jTree.getLastSelectedPathComponent()).removeAllChildren();
                File file = new File(((MyTreeNode)jTree.getLastSelectedPathComponent()).getRealPath().toString().trim());
                Vector<ChannelSftp.LsEntry> folderVector = ftpService.getFolderList(file.getPath());
                Vector<ChannelSftp.LsEntry> fileVector = ftpService.getFileList(file.getPath());
                fileJTree.setByVector(file.getName(),file.getPath(),fileVector);
                Iterator<ChannelSftp.LsEntry> iterator  = folderVector.iterator();
                MyTreeNode buf;
                String fileName;
                while(iterator.hasNext()){
                	fileName = iterator.next().getFilename();
                	buf = new MyTreeNode(fileName);
                	buf.setRealPath(((MyTreeNode)jTree.getLastSelectedPathComponent()).getRealPath()+"/"+fileName);
                	buf.add(new MyTreeNode(""));
                	((MyTreeNode)jTree.getLastSelectedPathComponent()).add(buf);
                	
                }
                if(((MyTreeNode)jTree.getLastSelectedPathComponent()).getChildCount() == 0){
                	((MyTreeNode)jTree.getLastSelectedPathComponent()).add(new MyTreeNode(""));
                }
                jTree.updateUI();
            }
        }else{
            if(jTree.getLastSelectedPathComponent() != null){
                File file = new File(((MyTreeNode)jTree.getLastSelectedPathComponent()).getRealPath().toString());
                fileJTree.initJTree(file.getName(),file.getPath());
            }
        }

    }

    public void setServerYn(boolean serverYn) {
        this.serverYn = serverYn;
    }

    public void setFtpService(FtpService ftpService) {
        this.ftpService = ftpService;
    }

	public JTree getjTree() {
		return jTree;
	}

	public void setjTree(JTree jTree) {
		this.jTree = jTree;
	}

	public MyFileJTree getFileJTree() {
		return fileJTree;
	}

	public void setFileJTree(MyFileJTree fileJTree) {
		this.fileJTree = fileJTree;
	}

    
    
}
