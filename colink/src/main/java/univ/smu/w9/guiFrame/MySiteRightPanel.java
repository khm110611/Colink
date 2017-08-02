package univ.smu.w9.guiFrame;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;

public class MySiteRightPanel extends JPanel {

    private FtpService ftpService;
    private SshService sshService;
    private FileService fileService;

    public MySiteRightPanel(){
        this.ftpService = ftpService;
        this.sshService = sshService;
        this.fileService = fileService;
        this.setVisible(true);
        this.setSize(400, 630);
    }
}
