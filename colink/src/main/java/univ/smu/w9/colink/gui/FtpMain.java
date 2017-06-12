package univ.smu.w9.colink.gui;

import javax.swing.JFrame;

import univ.smu.w9.colink.guiComponent.MyMenuBar;

/**
 * FTP 메인화면
 * @author "SukHwanYoon"
 *
 */
public class FtpMain extends JFrame{

    MyMenuBar menuBar;
    public FtpMain(MyMenuBar menuBar){
        // component init
        this.menuBar = menuBar;

        // frame init
        this.setSize(600,400);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
