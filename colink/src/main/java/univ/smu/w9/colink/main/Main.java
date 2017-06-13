package univ.smu.w9.colink.main;

import javax.swing.JFrame;
import javax.swing.LookAndFeel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.alee.laf.WebLookAndFeel;

import univ.smu.w9.colink.gui.FtpMain;
import univ.smu.w9.colink.guiComponent.MyMenuBar;
import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.colink.vo.UserVO;
import univ.smu.w9.common.CommonGUI;
import univ.smu.w9.common.CommonString;

public class Main extends JFrame{

    FtpMain ftpMain;

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
            WebLookAndFeel.globalMenuFont = CommonGUI.PLAIN_PARA_FONT;
            UIManager.setLookAndFeel("com.alee.laf.WebLookAndFeel");

        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InstantiationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //service Init
        ftpService = new FtpService(ftpUser);
        sshService = new SshService(sshUser);
        fileService = new FileService();

        // GUI - Jpanel init
        ftpMain = new FtpMain(ftpService, fileService);

        // GUI - component init
        menuBar = new MyMenuBar(ftpService, sshService);

        this.add(ftpMain);
        this.setSize(1000,800);
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
