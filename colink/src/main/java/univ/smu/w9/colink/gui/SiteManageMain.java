package univ.smu.w9.colink.gui;


import javax.swing.JFrame;
import javax.swing.JPanel;

import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.guiFrame.MySiteLeftPanel;
import univ.smu.w9.guiFrame.MySiteRightPanel;

/**
 * 사이트 관리자 화면
 * @author "SukHwanYoon"
 *
 */
public class SiteManageMain extends JFrame{

    /**
     *
     */
    private static final long serialVersionUID = -3338743645265699488L;

    private FtpService ftpService;
    private SshService sshService;
    private FileService fileService;

    private MySiteLeftPanel mySiteLeftPanel;
    private MySiteRightPanel mySiteRightPanel;

    public SiteManageMain(FtpService ftpService,SshService sshService,FileService fileService) {
        this.ftpService = ftpService;
        this.sshService = sshService;
        this.fileService = fileService;
        mySiteLeftPanel = new MySiteLeftPanel(fileService);
        mySiteRightPanel = new MySiteRightPanel();
        this.add(mySiteLeftPanel);
        this.add(mySiteRightPanel);
        this.setSize(600,700);
        this.changeVisible();
    }

    /**
     * 창 닫고/열기
     */
    public void changeVisible(){
        if(this.isVisible()){
            this.setVisible(false);
        }else{
            this.setVisible(true);
        }
    }

}
