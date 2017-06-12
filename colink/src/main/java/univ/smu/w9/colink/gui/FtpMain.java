package univ.smu.w9.colink.gui;

import javax.swing.JPanel;

import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;

/**
 * FTP 메인화면
 * @author "SukHwanYoon"
 *
 */
public class FtpMain extends JPanel{

    FtpService ftpService;

    FileService fileService;
    public FtpMain(FtpService ftpService,FileService fileService){
        // component Init

        // service Init
        this.ftpService = ftpService;
        this.fileService = fileService;

        // frame init
        this.setSize(600,400);
        this.setVisible(true);
    }
}
