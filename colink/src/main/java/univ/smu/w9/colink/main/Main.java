package univ.smu.w9.colink.main;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.alee.laf.WebLookAndFeel;

import univ.smu.w9.colink.gui.FtpMain;
import univ.smu.w9.colink.gui.SiteManageMain;
import univ.smu.w9.colink.gui.SshMain;
import univ.smu.w9.colink.guiComponent.MyMenuBar;
import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.colink.vo.UserVO;
import univ.smu.w9.common.CommonGUI;
import java.awt.BorderLayout;

public class Main extends JFrame{

    /**
     *
     */
    private static final long serialVersionUID = -322639851091514382L;

    FtpMain ftpMain;
    SshMain sshMain;
    //ssh 연결 정보
    UserVO sshUser;
    //ftp 연결 정보
    UserVO ftpUser;

    //FTP Service
    FtpService ftpService;
    //SSH Service
    SshService sshService;
    //File Service
    FileService fileService;

    //menuBar
    MyMenuBar menuBar;

    // Init Main Frame
    public Main() {

        // UI Theme 설정
        try {
            WebLookAndFeel.globalMenuFont = CommonGUI.PLAIN_NORMAL_FONT;
            WebLookAndFeel.globalControlFont = CommonGUI.PLAIN_SMALL_FONT;
            UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        //service Init
        ftpService = new FtpService(ftpUser);
        sshService = new SshService(sshUser);
        fileService = new FileService();

        // GUI - Jpanel init
        ftpMain = new FtpMain(ftpService, fileService);
        ftpMain.setLocation(0, 0);
        ftpMain.setSize(1000, 450);
        sshMain = new SshMain(sshService);
        sshMain.setSize(1000, 199);
        sshMain.setLocation(0, 447);
        // GUI - component init
        menuBar = new MyMenuBar(ftpService, sshService,fileService);
        menuBar.setMain(this);

        getContentPane().setLayout(null);
        getContentPane().add(ftpMain);
        getContentPane().add(sshMain);
        this.setSize(1016,716);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(menuBar);
        this.setVisible(true);
        this.setTitle("Colink");
        this.repaint();

    }

    public static void main(String[] args) {
        new Main();
    }

}
