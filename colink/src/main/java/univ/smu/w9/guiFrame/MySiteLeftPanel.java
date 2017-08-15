package univ.smu.w9.guiFrame;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import univ.smu.w9.colink.guiComponent.MySiteTree;
import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.colink.vo.SiteVO;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 사이트 관리자 좌측
 * @author "SukHwanYoon"
 *
 */
public class MySiteLeftPanel extends JPanel implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 3860473238930897509L;

    /**
     * 사이트 목록
     */
    private MySiteTree mySiteTree;

    private FtpService ftpService;
    private SshService sshService;
    private FileService fileService;

    private JButton newSite;
    private JButton changeName;
    private JButton deleteSite;
    
    private MySiteRightPanel mySiteRightPanel;

    public void setMySiteRightPanel(MySiteRightPanel mySiteRightPanel) {
		this.mySiteRightPanel = mySiteRightPanel;
	}
    
    public MySiteLeftPanel(FileService fileService) {
        this.setLayout(null);
        this.fileService = fileService;
        mySiteTree = new MySiteTree(fileService);
        
        mySiteTree.getjScroll().setSize(190,400);
        mySiteTree.getjScroll().setLocation(0, 0);
        this.add(mySiteTree.getjScroll());

        newSite = new JButton("새 사이트");
        newSite.setBounds(12, 435, 160, 36);
        newSite.addActionListener(this);
        this.add(newSite);

        changeName = new JButton("이름 바꾸기");
        changeName.setBounds(12, 481, 160, 36);
        changeName.addActionListener(this);
        this.add(changeName);

        deleteSite = new JButton("삭 제");
        deleteSite.setBounds(12, 527, 160, 36);
        deleteSite.addActionListener(this);
        this.add(deleteSite);

        this.setVisible(true);
        this.setSize(200,630);

    }
    
    public void actionPerformed(ActionEvent e) {
    	String text = ((JButton)e.getSource()).getText();
    	if(text.equals("새 사이트")){
    		SiteVO siteVO = mySiteRightPanel.returnSiteVO();
    		if(siteVO == null){
    			return;
    		}else{
    			mySiteTree.addSiteList(siteVO);	
    		}
    	}else if(text.equals("삭 제")){
    		boolean flag = mySiteTree.deleteSiteList();
    		if(!flag){
    			JOptionPane.showMessageDialog(null, "사이트를 선택해주세요.", "선택 오류", JOptionPane.ERROR_MESSAGE);
    		}else{
    			JOptionPane.showMessageDialog(null,"사이트 삭제 완료 되었습니다.");
    		}
    	}
    }
	
}
