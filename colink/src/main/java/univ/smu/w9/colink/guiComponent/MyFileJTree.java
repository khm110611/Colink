package univ.smu.w9.colink.guiComponent;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
 * File tree
 * @author "SukHwanYoon"
 *
 */
public class MyFileJTree implements TreeSelectionListener,MouseListener{

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
     * jTreeModel
     */
    private DefaultTreeModel jTreeModel;

    /**
     * Server : true , local : false
     */
    private boolean ServerYn;
    
    private MyFileJTree localJTree;
    
    private FtpService ftpService;
    
    private MyFolderJTree myFolderJtree;
    
    private MyFolderJTree mySFolderJtree;
    /**
     * jTree 초기화
     */
    public MyFileJTree() {
        root = new MyTreeNode();
        jTree = new JTree(root);
        jTree.addMouseListener(this);
        jTreeModel = (DefaultTreeModel) jTree.getModel();
        jScroll = new JScrollPane(jTree);
    }

    /**
     * jTree 초기화
     * @param rootName
     */
    public MyFileJTree(String rootName){


        root = new MyTreeNode(rootName);
        makeTreeModel(rootName,root);

        jTree = new JTree(root);
        jTreeModel = (DefaultTreeModel) jTree.getModel();
        jTree.addTreeSelectionListener(this);
        jTree.addMouseListener(this);
        jScroll = new JScrollPane(jTree);

    }

    /**
     * JTree 재 초기화
     * @param rootName
     * @param files
     */
    public void initJTree(String folderName,String rootName){
        root = new MyTreeNode(folderName);
        root.setRealPath(rootName);
        makeTreeModel(rootName,root);
        jTreeModel.setRoot(root);
    }

    /**
     * Make Tree Model
     * @param rootName
     */
    public void makeTreeModel(String rootName,MyTreeNode root){
        //경로의 전체 파일 검색
        File rootFile = new File(rootName);
        File[] files = rootFile.listFiles();
        if(files == null || files.length == 0){
        	root = new MyTreeNode(rootName);
        	return;
        }
        MyTreeNode dmtBuf;
        for(int i=0;i<files.length;i++){
             if(files[i].isDirectory()){
                 continue;
             }
            dmtBuf = new MyTreeNode(files[i].getName());
            dmtBuf.setRealPath(files[i].getPath());
            root.add(dmtBuf);
        }
    }

    /**
     * 벡터로 초기화
     * @param fileName
     * @param rootName
     * @param vector
     */
    public void setByVector(String fileName,String rootName,Vector<ChannelSftp.LsEntry> vector){
        root = new MyTreeNode(fileName);
        root.setRealPath(rootName);
        
        Iterator<ChannelSftp.LsEntry> iterator = vector.iterator();
        MyTreeNode mtnBuf;
        ChannelSftp.LsEntry lsBuf;
        while(iterator.hasNext()){
        	lsBuf = iterator.next();
        	mtnBuf = new MyTreeNode(lsBuf.getFilename());
            mtnBuf.setRealPath(rootName+"/"+lsBuf.getFilename());
            mtnBuf.setFileName(lsBuf.getFilename());
        	root.add(mtnBuf);
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
    	
    }


    public void setServerYn(boolean serverYn) {
        ServerYn = serverYn;
    }

	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 2) {
			if(this.ServerYn){
				//서버 일때
				MyTreeNode selectedTreeNode = (MyTreeNode) jTree.getLastSelectedPathComponent();
				//파일 서버 경로, 파일명 , 다운로드 위치
				ftpService.download(selectedTreeNode.getRealPath(),
						selectedTreeNode.getFileName(),
						localJTree.root.getRealPath());
			}else{
				if(ftpService.isConnected()){
					MyTreeNode selectedTreeNode = (MyTreeNode) jTree.getLastSelectedPathComponent();
					ftpService.upload(localJTree.root.getRealPath()
							, new File(((MyTreeNode)jTree.getLastSelectedPathComponent()).getRealPath().toString()));	
				}
			}
		}
			
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public FtpService getFtpService() {
		return ftpService;
	}

	public void setFtpService(FtpService ftpService) {
		this.ftpService = ftpService;
	}

	public MyFileJTree getLocalJTree() {
		return localJTree;
	}

	public void setLocalJTree(MyFileJTree localJTree) {
		this.localJTree = localJTree;
	}

	

}
