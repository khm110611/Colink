package univ.smu.w9.colink.guiComponent;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.vo.SiteVO;

/**
 * 사이트 저장 트리 구조
 * @author "SukHwanYoon"
 *
 */
public class MySiteTree {

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

    public MySiteTree(FileService fileService) {
        try {
            root = new DefaultMutableTreeNode("사이트 목록");
            jtree = new JTree(root);
            jScroll = new JScrollPane(jtree);
            this.fileService = fileService;
            siteList = fileService.getSiteList();
        } catch (IOException e) {
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
    }

    /**
     * 사이트 리스트에 사이트 추가하기
     */
    public void addSiteList(SiteVO siteVO){
        siteList.add(siteVO);
    }

    /**
     * 사이트 리스트에서 사이트 삭제하기
     * @param idx : 사이트 저장 번호
     */
    public void deleteSiteList(int idx){
        siteList.remove(idx);
        makeTreeNode();
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



}
