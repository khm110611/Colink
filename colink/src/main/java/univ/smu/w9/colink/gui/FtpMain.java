package univ.smu.w9.colink.gui;

import javax.swing.JPanel;

import univ.smu.w9.colink.guiComponent.MyFileJTree;
import univ.smu.w9.colink.guiComponent.MyFolderJTree;
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

    MyFolderJTree userFolderTree;
    MyFileJTree userFileTree;
    MyFolderJTree serverFolderTree;
    MyFileJTree serverFileTree;

    FileService fileService;

    public FtpMain(FtpService ftpService,FileService fileService){
        // component Init

        // service Init
        this.ftpService = ftpService;
        this.fileService = fileService;

        // GUI Component
        userFileTree   = new MyFileJTree(CommonString.DESKTOP_PATH);
        userFolderTree = new MyFolderJTree(CommonString.DESKTOP_PATH,userFileTree);


        userFolderTree.getjScroll().setLocation(0,0);
        userFolderTree.getjScroll().setSize(500, 250);
        userFileTree.getjScroll().setLocation(0, 250);
        userFileTree.getjScroll().setSize(500, 200);

        serverFileTree   = new MyFileJTree();
        serverFolderTree = new MyFolderJTree();


        serverFolderTree.getjScroll().setLocation(500,0);
        serverFolderTree.getjScroll().setSize(500, 250);
        serverFileTree.getjScroll().setLocation(500, 250);
        serverFileTree.getjScroll().setSize(500, 200);

        // Add component
        this.add(userFolderTree.getjScroll());
        this.add(userFileTree.getjScroll());

        this.add(serverFolderTree.getjScroll());
        this.add(serverFileTree.getjScroll());

        ftpService.setFtpFolderTree(serverFolderTree);
        ftpService.setFtpFileTree(serverFileTree);

        // frame init
        this.setLayout(null);
        this.setVisible(true);
    }
}
