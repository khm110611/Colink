package univ.smu.w9.colink.guiComponent;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.common.CommonGUI;

public class MySshArea extends JTextArea implements KeyListener{

    /**
     *
     */
    private static final long serialVersionUID = -7940892807474431615L;

    private SshService sshService;

    public MySshArea(SshService sshService){
        this.sshService = sshService;
        this.setBackground(Color.BLACK);
        this.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        this.setForeground(Color.WHITE);
        this.setSize(1000, 220);
        this.setVisible(true);
        this.addKeyListener(this);
    }

    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub

    }

    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub

    }
}
