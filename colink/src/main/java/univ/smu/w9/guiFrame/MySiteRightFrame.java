package univ.smu.w9.guiFrame;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;

import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;

public class MySiteRightFrame extends JFrame {

    private FtpService ftpService;
    private SshService sshService;
    private FileService fileService;

    public MySiteRightFrame(){
        this.ftpService = ftpService;
        this.sshService = sshService;
        this.fileService = fileService;
        this.setVisible(true);
        this.setSize(400, 630);
    }
}
