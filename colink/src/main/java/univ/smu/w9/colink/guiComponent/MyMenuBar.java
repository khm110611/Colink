package univ.smu.w9.colink.guiComponent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.common.CommonString;

/**
 * MenuBar class
 * @author "SukHwanYoon"
 *
 */
public class MyMenuBar extends JMenuBar implements MenuListener{

    private FtpService ftpService;
    private SshService sshService;

    public MyMenuBar(FtpService ftpService,SshService sshService) {
        this.ftpService = ftpService;
        this.sshService = sshService;
        initMenuBar();

    }

    //초기화
    public void initMenuBar(){

        // 파일
        JMenu file   = new JMenu("파일");
        file.add(new JMenuItem("사이트 관리자"));
        file.add(new JMenuItem("종료"));
        file.getItem(0).setIcon(UIManager.getIcon("FileChooser.listViewIcon"));
        file.getItem(1).setIcon(UIManager.getIcon(""));
        file.addMenuListener(this);

        // 보기
        JMenu view   = new JMenu("보기");
        view.add(new JMenuItem("파일 목록 상태 표시줄"));
        view.add(new JMenuItem("파일 목록창"));
        view.add(new JMenuItem("SSH창"));
        view.addMenuListener(this);

        // 전송
        JMenu send   = new JMenu("전송");
        JMenu sendType   = new JMenu("전송 유형");
        sendType.add(new JMenuItem("자동"));
        sendType.add(new JMenuItem("아스키"));
        sendType.add(new JMenuItem("바이너리"));
        send.add(sendType);
        send.addMenuListener(this);

        // 서버
        JMenu server = new JMenu("서버");
        server.add(new JMenuItem("다시 연결"));
        server.add(new JMenuItem("연결 종료"));
        server.addMenuListener(this);

        this.add(file);
        this.add(view);
        this.add(send);
        this.add(server);

        this.setVisible(true);
    }

    // jMenuBar return
    public JMenuBar getJMenuBar(){
        return this;
    }

    public void menuSelected(MenuEvent e) {
        JMenuItem jmib = (JMenuItem) e.getSource();
        String jmibT = jmib.getText();
        if(jmibT.equals("사이트 관리자")){

        }else if(jmib.equals("종료")){
            System.exit(-1);
        }else if(jmib.equals("파일 목록 상태 표시줄")){

        }else if(jmib.equals("파일 목록창")){

        }else if(jmib.equals("SSH창")){

        }else if(jmib.equals("자동")){

        }else if(jmib.equals("아스키")){

        }else if(jmib.equals("바이너리")){

        }else if(jmib.equals("다시 연결")){

        }else if(jmib.equals("연결 종료")){

        }
    }

    public void menuCanceled(MenuEvent e) {
        // TODO Auto-generated method stub

    }

    public void menuDeselected(MenuEvent e) {
        // TODO Auto-generated method stub

    }


}
