package univ.smu.w9.colink.guiComponent;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import univ.smu.w9.colink.common.CommonGUI;
import univ.smu.w9.colink.service.SshService;

public class MySshField extends JTextArea implements KeyListener{

    /**
     *
     */
    private static final long serialVersionUID = -7940892807474431615L;

    private SshService sshService;

    public MySshField(SshService sshService){
        this.sshService = sshService;
        this.setBackground(Color.BLACK);
        this.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        this.setForeground(Color.WHITE);
        this.setVisible(true);
        this.addKeyListener(this);
        this.setBorder(new LineBorder(Color.BLACK,0));
        this.getDocument().putProperty("filterNewlines", Boolean.TRUE);
    }

    public synchronized void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!this.getText().equals("")){
                sshService.sendExec(this.getText());
                this.setText("");
            }
        }

    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
