package univ.smu.w9.colink.gui;

import javax.swing.JPanel;

import univ.smu.w9.colink.guiComponent.MySshField;
import univ.smu.w9.colink.guiComponent.MySshArea;
import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.colink.vo.SiteVO;

/**
 * SSH 메인화면
 * @author "SukHwanYoon"
 *
 */
public class SshMain extends JPanel{

    private static final long serialVersionUID = -1593998326482886669L;

    private SshService sshService;

    private MySshField mySshField;
    private MySshArea mySshArea;
    public SshMain(SshService sshService){
        this.sshService = sshService;
        mySshField = new MySshField(sshService);
        mySshArea = new MySshArea();
        this.sshService.setMySshArea(mySshArea);
        this.add(mySshField);
        this.add(mySshArea);
        this.setLayout(null);
        this.setVisible(true);
    }


}
