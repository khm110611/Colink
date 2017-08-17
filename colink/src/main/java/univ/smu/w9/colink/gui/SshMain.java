package univ.smu.w9.colink.gui;

import java.awt.Color;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

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
    private JScrollPane jsrp;
    public SshMain(SshService sshService){
        this.sshService = sshService;
        mySshField = new MySshField(sshService);
        mySshField.setLocation(0, 170);
        mySshArea = new MySshArea();
        mySshArea.setBounds(0, 0, 1000, 173);
        jsrp = new JScrollPane(mySshArea);
        jsrp.setBounds(0, 0, 1000, 173);
        jsrp.setBackground(Color.BLACK);
        sshService.setJscroll(jsrp);
        this.sshService.setMySshArea(mySshArea);
        this.add(mySshField);

        this.add(jsrp);
        this.setLayout(null);
        this.setVisible(true);
    }


}
