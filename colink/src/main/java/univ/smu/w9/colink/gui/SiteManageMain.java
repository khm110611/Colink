package univ.smu.w9.colink.gui;


import javax.swing.JPanel;

import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;

/**
 * 사이트 관리자 화면
 * @author "SukHwanYoon"
 *
 */
public class SiteManageMain extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = -3338743645265699488L;

    private FtpService ftpService;
    private SshService sshService;
    private FileService fileService;

    public SiteManageMain(FtpService ftpService,SshService sshService) {
        this.ftpService = ftpService;
        this.sshService = sshService;

        this.setSize(600,700);
        changeVisible();
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
