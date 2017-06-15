package univ.smu.w9.colink.gui;

import javax.swing.JPanel;
import univ.smu.w9.colink.guiComponent.MyJTree;
import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.common.CommonString;

/**
 * FTP 메인화면
 * @author "SukHwanYoon"
 *
 */
public class FtpMain extends JPanel{

    /**
     *
     */
    private static final long serialVersionUID = 6323641787119254657L;

    FtpService ftpService;

    MyJTree userFolderTree;
    MyJTree userFileTree;
    MyJTree serverFolderTree;
    MyJTree serverFileTree;

    FileService fileService;

    public FtpMain(FtpService ftpService,FileService fileService){
        // component Init

        // service Init
        this.ftpService = ftpService;
        this.fileService = fileService;

        // GUI Component
        userFolderTree = new MyJTree(CommonString.DESKTOP_PATH,1);
        userFileTree   = new MyJTree(CommonString.DESKTOP_PATH,2);

        userFolderTree.getjScroll().setLocation(0,0);
        userFolderTree.getjScroll().setSize(500, 350);
        userFileTree.getjScroll().setLocation(0, 350);
        userFileTree.getjScroll().setSize(500, 200);

        serverFolderTree = new MyJTree();
        serverFileTree   = new MyJTree();

        serverFolderTree.getjScroll().setLocation(500,0);
        serverFolderTree.getjScroll().setSize(500, 350);
        serverFileTree.getjScroll().setLocation(500, 350);
        serverFileTree.getjScroll().setSize(500, 200);

        // Add component
        this.add(userFolderTree.getjScroll());
        this.add(userFileTree.getjScroll());

        this.add(serverFolderTree.getjScroll());
        this.add(serverFileTree.getjScroll());

        // frame init
        this.setLayout(null);
        this.setSize(1000,720);
        this.setVisible(true);
    }
}
