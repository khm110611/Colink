package univ.smu.w9.colink.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.common.CommonGUI;
import univ.smu.w9.guiFrame.MySiteLeftPanel;
import univ.smu.w9.guiFrame.MySiteRightPanel;

/**
 * 사이트 관리자 화면
 * @author "SukHwanYoon"
 *
 */
public class SiteManageMain extends JFrame implements ActionListener{

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
        mySiteLeftPanel.setBounds(0, 0, 200, 604);
        mySiteRightPanel.setSize(368, 604);
        mySiteRightPanel.setLocation(216, 0);


        JButton ftpConBtn = new JButton("FTP 연결");
        ftpConBtn.setSize(117, 30);
        ftpConBtn.setLocation(12, 621);
        ftpConBtn.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpConBtn.addActionListener(this);
        getContentPane().add(ftpConBtn);

        JButton sshConBtn = new JButton("SSH 연결");
        sshConBtn.setSize(100, 30);
        sshConBtn.setLocation(145, 621);
        sshConBtn.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        sshConBtn.addActionListener(this);
        getContentPane().add(sshConBtn);

        JButton bothConBtn = new JButton("둘다 연결");
        bothConBtn.setSize(100, 30);
        bothConBtn.setLocation(267, 621);
        bothConBtn.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        bothConBtn.addActionListener(this);
        getContentPane().add(bothConBtn);

        JButton closeBtn = new JButton("취소");
        closeBtn.setSize(100, 30);
        closeBtn.setLocation(472, 621);
        closeBtn.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        closeBtn.addActionListener(this);
        getContentPane().add(closeBtn);

        getContentPane().setLayout(null);
        getContentPane().add(mySiteLeftPanel);
        getContentPane().add(mySiteRightPanel);
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

    public void actionPerformed(ActionEvent e) {
         String text = (String)((JButton)e.getSource()).getText();
         if(text.equals("FTP 연결")){
             ftpService.setFtpUser(mySiteRightPanel.getUserVO()[1]);
             ftpService.connect();
         }else if(text.equals("SSH 연결")){
             sshService.setSshUser(mySiteRightPanel.getUserVO()[0]);
             sshService.connect();
         }else if(text.equals("둘다 연결")){
             ftpService.setFtpUser(mySiteRightPanel.getUserVO()[1]);
             ftpService.connect();
             sshService.setSshUser(mySiteRightPanel.getUserVO()[0]);
             sshService.connect();
         }
         dispose();

    }

}
