package univ.smu.w9.colink.guiComponent;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import univ.smu.w9.colink.gui.SiteManageMain;
import univ.smu.w9.colink.main.Main;
import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.common.CommonString;

/**
 * MenuBar class
 * @author "SukHwanYoon"
 *
 */
public class MyMenuBar extends JMenuBar implements ActionListener{

    /**
     *
     */
    private static final long serialVersionUID = -5351023900638347389L;

    private FtpService ftpService;
    private SshService sshService;
    private FileService fileService;
    
    private Main main;
    
	public void setMain(Main main) {
		this.main = main;
	}

	public MyMenuBar(FtpService ftpService,SshService sshService,FileService fileService) {
        this.ftpService = ftpService;
        this.sshService = sshService;
        this.fileService = fileService;
        initMenuBar();

    }

    //초기화
    public void initMenuBar(){

        // 파일
        JMenu file   = new JMenu("파일");
        JMenuItem siteManager = new JMenuItem("사이트 관리자");
        JMenuItem exit = new JMenuItem("종료");
        siteManager.addActionListener(this);
        exit.addActionListener(this);
        file.add(siteManager);
        file.add(exit);

        // 서버
        JMenu server = new JMenu("서버");
        JMenuItem reCon = new JMenuItem("다시 연결");
        JMenuItem disCon = new JMenuItem("연결 종료");
        reCon.addActionListener(this);
        disCon.addActionListener(this);
        server.add(reCon);
        server.add(disCon);

        this.add(file);
        this.add(server);

        this.setVisible(true);
    }

    // jMenuBar return
    public JMenuBar getJMenuBar(){
        return this;
    }


    public void actionPerformed(ActionEvent e) {
        JMenuItem jmib = (JMenuItem) e.getSource();
        String jmibT = jmib.getText();
        if(jmibT.equals("사이트 관리자")){
            new SiteManageMain(ftpService, sshService,fileService);
        }else if(jmibT.equals("종료")){
        	System.exit(0);
        }else if(jmibT.equals("다시 연결")){
            boolean result = sshService.reConnect();
            if(!result){
                System.out.println("실패");
            }
            result = ftpService.reConnect();
            if(!result){
                System.out.println("실패");
            }
        }else if(jmibT.equals("연결 종료")){
            sshService.disConnect();
            ftpService.disConnect();
        }
    }


}
