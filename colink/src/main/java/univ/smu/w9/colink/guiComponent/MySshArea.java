package univ.smu.w9.colink.guiComponent;

import java.awt.Color;

import javax.swing.JTextArea;

import univ.smu.w9.common.CommonGUI;

public class MySshArea extends JTextArea{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7940892807474431615L;

	public MySshArea() {
        this.setBackground(Color.BLACK);
        this.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        this.setForeground(Color.WHITE);
        this.setSize(1000, 200);
        this.setEditable(false);
        this.setVisible(true);
    }


}
