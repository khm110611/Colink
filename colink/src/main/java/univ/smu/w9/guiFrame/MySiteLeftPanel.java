package univ.smu.w9.guiFrame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import univ.smu.w9.colink.guiComponent.MySiteTree;
import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MySiteLeftPanel extends JPanel implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = 3860473238930897509L;

    /**
     * 사이트 목록
     */
    private MySiteTree mySiteTree;

    private FtpService ftpService;
    private SshService sshService;
    private FileService fileService;

    private JButton newSite;
    private JButton changeName;
    private JButton deleteSite;

    public MySiteLeftPanel(FileService fileService) {
        this.setLayout(null);
        this.fileService = fileService;
        mySiteTree = new MySiteTree(fileService);

        mySiteTree.getjScroll().setSize(190,400);
        mySiteTree.getjScroll().setLocation(0, 0);
        this.add(mySiteTree.getjScroll());

        newSite = new JButton("새 사이트");
        newSite.setBounds(12, 435, 160, 36);
        newSite.addActionListener(this);
        this.add(newSite);

        changeName = new JButton("이름 바꾸기");
        changeName.setBounds(12, 481, 160, 36);
        changeName.addActionListener(this);
        this.add(changeName);

        deleteSite = new JButton("삭 제");
        deleteSite.setBounds(12, 527, 160, 36);
        deleteSite.addActionListener(this);
        this.add(deleteSite);

        this.setVisible(true);
        this.setSize(200,630);

    }
    public void actionPerformed(ActionEvent e) {

    }
}
