package univ.smu.w9.colink.guiComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;

import univ.smu.w9.colink.guiFrame.MySiteRightPanel;
import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.vo.SiteVO;

/**
 * 사이트 저장 트리 구조
 * @author "SukHwanYoon"
 *
 */
public class MySiteTree implements MouseListener{

    /**
     *  jTree
     */
    private JTree jtree;

    /**
     * 루트(최상위) 트리 노드
     */
    private DefaultMutableTreeNode root;

    /**
     * 스크롤판넬
     */
    private JScrollPane jScroll;

    /**
     * 사이트 리스트
     */
    private List<SiteVO> siteList;

    private FileService fileService;
    
    /**
     * 오른쪽 판넬
     */
    private MySiteRightPanel mySiteRightPanel;
    
    public MySiteTree(FileService fileService) {
        try {
            root = new DefaultMutableTreeNode("사이트 목록");
            jtree = new JTree(root);
            jtree.addMouseListener(this);
            jScroll = new JScrollPane(jtree);
            this.fileService = fileService;
            siteList = fileService.getSiteList();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(!siteList.isEmpty()){
            makeTreeNode();
        }

    }

    /**
     * 사이트 리스트로 루트노드 만들기
     * @param siteVector
     */
    public void makeTreeNode(){
        root.removeAllChildren();
        Iterator<SiteVO> iterator = siteList.iterator();
        DefaultMutableTreeNode buf;
        while(iterator.hasNext()){
            buf = new DefaultMutableTreeNode(iterator.next().getSiteName());
            root.add(buf);
        }
        jtree.updateUI();
    }

    /**
     * 사이트 리스트에 사이트 추가하기
     */
    public boolean addSiteList(SiteVO siteVO){
    	siteList.add(siteVO);
        root.add(new DefaultMutableTreeNode(siteVO.getSiteName()));
        jtree.updateUI();
        return true;
    }
    
    /**
     * 사이트 리스트에 사이트 추가하기
     */
    public boolean addSiteList(List<SiteVO> siteList){
    	this.siteList = siteList;
    	Iterator<SiteVO> iterator = siteList.iterator();
    	while(iterator.hasNext()){
    		root.add(new DefaultMutableTreeNode(iterator.next().getSiteName()));	
    	}
        jtree.updateUI();
        return true;
    }

    /**
     * 사이트 리스트에서 사이트 삭제하기
     * @param idx : 사이트 저장 번호
     */
    public boolean deleteSiteList(){
    	if(jtree.getLastSelectedPathComponent() == null || jtree.getLastSelectedPathComponent().toString().equals("사이트 목록")){
    		return false;
    	}
    	DefaultMutableTreeNode delBuf =  (DefaultMutableTreeNode)jtree.getLastSelectedPathComponent();
    	
    	//목록에서 삭제
    	Iterator<SiteVO> iterator = siteList.iterator();
    	SiteVO buf;
    	while(iterator.hasNext()){
    		buf = iterator.next();
    		if(buf.getSiteName().equals(delBuf.getPath())){
    			iterator.remove();
    			break;
    		}
    	}
    	
    	// 트리에서 삭제
        siteList.remove(root.getIndex(delBuf));
        makeTreeNode();
        return true;
    }

    /**
     * 사이트 리스트파일에 저장하기
     */
    public void saveSiteList(){
        try {
            fileService.saveSiteList(siteList);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * 트리 return
     * @return
     */
    public JScrollPane getjScroll() {
        return jScroll;
    }

	public List<SiteVO> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<SiteVO> siteList) {
		this.siteList = siteList;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if(e.getClickCount() == 2){
			DefaultMutableTreeNode selectedTreeNode = (DefaultMutableTreeNode) jtree.getLastSelectedPathComponent();
			if(!selectedTreeNode.equals(root)){
				mySiteRightPanel.setSiteVo(siteList.get(root.getIndex(selectedTreeNode)));
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public MySiteRightPanel getMySiteRightPanel() {
		return mySiteRightPanel;
	}

	public void setMySiteRightPanel(MySiteRightPanel mySiteRightPanel) {
		this.mySiteRightPanel = mySiteRightPanel;
	}

		
	
}
