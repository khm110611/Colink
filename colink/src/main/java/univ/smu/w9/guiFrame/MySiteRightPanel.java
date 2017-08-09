package univ.smu.w9.guiFrame;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.colink.vo.UserVO;
import univ.smu.w9.common.CommonGUI;
import javax.swing.JToggleButton;

/**
 * 사이트 관리자 우측
 * @author "SukHwanYoon"
 *
 */
public class MySiteRightPanel extends JPanel implements ActionListener{

    private FtpService ftpService;
    private SshService sshService;
    private FileService fileService;

    /**
     * toggle Flag  : true -> ssh , false -> ftp
     */
    private boolean sshFlag;
    /**
     * ssh 유저 정보 저장 객체
     */
    private UserVO sshUser;
    /**
     * ftp 유저 정보 저장 객체
     */
    private UserVO ftpUser;

    /**
     * 호스트 필드
     */
    private JTextField sshHostField;

    /**
     * 포트번호 필드
     */
    private JTextField sshPortField;

    /**
     *  유저 아이디 필드
     */
    private JTextField sshUserField;

    /**
     * 비밀번호 필드
     */
    private JTextField sshPwField;

    /**
     * Pem 파일 선택
     */
    private JFileChooser sshPemFileChooser;


    /**
     * 호스트 필드
     */
    private JTextField ftpHostField;

    /**
     * 포트번호 필드
     */
    private JTextField ftpPortField;

    /**
     *  유저 아이디 필드
     */
    private JTextField ftpUserField;

    /**
     * 비밀번호 필드
     */
    private JTextField ftpPwField;

    /**
     * Pem 파일 선택
     */
    private JFileChooser ftpPemFileChooser;

    private JToggleButton sshTgBtn;
    private JToggleButton ftpTgBtn;

    public MySiteRightPanel(){
        sshFlag = false;
        this.ftpService = ftpService;
        this.sshService = sshService;
        this.fileService = fileService;

        sshUser = new UserVO();
        ftpUser = new UserVO();

        this.setVisible(true);
        this.setSize(400, 630);
        this.setLayout(null);

        JLabel hostLabel = new JLabel("호스트");
        hostLabel.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        hostLabel.setSize(100, 30);
        hostLabel.setLocation(12, 46);

        sshHostField = new JTextField();
        sshHostField.setLocation(72, 47);
        sshHostField.setSize(190, 30);
        sshHostField.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        ftpHostField = new JTextField();
        ftpHostField.setLocation(72, 47);
        ftpHostField.setSize(190, 30);
        ftpHostField.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpHostField.setVisible(false);

        JLabel portLabel = new JLabel("포트");
        portLabel.setSize(35, 30);
        portLabel.setLocation(272, 46);

        sshPortField = new JTextField();
        sshPortField.setSize(41, 30);
        sshPortField.setLocation(319, 47);
        sshPortField.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        ftpPortField = new JTextField();
        ftpPortField.setSize(41, 30);
        ftpPortField.setLocation(319, 47);
        ftpPortField.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpPortField.setVisible(false);

        JLabel userLabel = new JLabel("사용자");
        userLabel.setSize(100, 30);
        userLabel.setLocation(12, 88);
        userLabel.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        sshUserField = new JTextField();
        sshUserField.setSize(288, 30);
        sshUserField.setLocation(72, 86);
        sshUserField.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        ftpUserField = new JTextField();
        ftpUserField.setSize(288, 30);
        ftpUserField.setLocation(72, 86);
        ftpUserField.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpUserField.setVisible(false);

        JLabel pwLabel   = new JLabel("비밀번호");
        pwLabel.setSize(100, 30);
        pwLabel.setLocation(12, 131);
        pwLabel.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        sshPwField = new JTextField();
        sshPwField.setSize(288, 30);
        sshPwField.setLocation(72, 132);
        sshPwField.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        ftpPwField = new JTextField();
        ftpPwField.setSize(288, 30);
        ftpPwField.setLocation(72, 132);
        ftpPwField.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpPwField.setVisible(false);

        sshPemFileChooser = new JFileChooser();
        sshPemFileChooser.setSize(288,30);
        sshPemFileChooser.setLocation(12, 260);
        sshPemFileChooser.setFont(CommonGUI.PLAIN_NORMAL_FONT);


        ftpPemFileChooser = new JFileChooser();
        ftpPemFileChooser.setSize(288,30);
        ftpPemFileChooser.setLocation(12, 260);
        ftpPemFileChooser.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpPemFileChooser.setVisible(false);


        JLabel bgLabel = new JLabel("Background Color");
        bgLabel.setBounds(12, 220, 143, 30);
        bgLabel.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        this.add(bgLabel);

        sshTgBtn = new JToggleButton("SSH");
        sshTgBtn.setBounds(12, 10, 135, 23);
        sshTgBtn.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        sshTgBtn.addActionListener(this);
        this.add(sshTgBtn);

        ftpTgBtn = new JToggleButton("FTP");
        ftpTgBtn.setBounds(156, 10, 135, 23);
        ftpTgBtn.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpTgBtn.addActionListener(this);
        this.add(ftpTgBtn);

        this.add(hostLabel);
        this.add(sshHostField);
        this.add(ftpHostField);


        this.add(portLabel);
        this.add(sshPortField);
        this.add(ftpPortField);

        this.add(userLabel);
        this.add(sshUserField);

        this.add(pwLabel);
        this.add(sshPwField);
        this.add(ftpPwField);

        this.add(sshPemFileChooser);
        this.add(ftpPemFileChooser);

    }

    /**
     * 유저 정보 저장
     * @param sshUser : ssh 유저 정보
     * @param ftpUser : ftp 유저 정보
     */
    public void loadUserVO(UserVO sshUser,UserVO ftpUser){
        this.sshUser = sshUser;
        this.ftpUser = ftpUser;

        this.sshHostField.setText(sshUser.getHostName());
        this.sshPortField.setText(Integer.toString(sshUser.getPort()));
        this.sshUserField.setText(sshUser.getUser());
        this.sshPwField.setText(sshUser.getPassword());
        if(sshUser.getPemFile() != null){
            this.sshPemFileChooser.setCurrentDirectory(new File(sshUser.getPemFile()));
        }

        this.ftpHostField.setText(ftpUser.getHostName());
        this.ftpPortField.setText(Integer.toString(ftpUser.getPort()));
        this.ftpUserField.setText(ftpUser.getUser());
        this.ftpPwField.setText(ftpUser.getPassword());
        if(ftpUser.getPemFile() != null){
            this.ftpPemFileChooser.setCurrentDirectory(new File(ftpUser.getPemFile()));
        }
    }

    /**
     * 유저 정보 return  0) ssh 1) ftp
     * @return
     */
    public UserVO[] getUserVO(){
        if(sshHostField.getText().equals("") || ftpHostField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"호스트 주소를 입력해주세요", "연결 실패", JOptionPane.ERROR_MESSAGE);
        }
        if(sshUserField.getText().equals("") || ftpUserField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"유저 ID를 입력해주세요", "연결 실패", JOptionPane.ERROR_MESSAGE);
        }
        UserVO[] userAry = new UserVO[2];

        userAry[0] = new UserVO();
        userAry[0].setHostName(sshHostField.getText());
        userAry[0].setPassword(sshPwField.getText());
        if(sshPortField.getText().equals("")){
            userAry[0].setPort(22);
        }else{
            userAry[0].setPort(Integer.parseInt(sshPortField.getText()));
        }
        userAry[0].setUser(sshUserField.getText());
        if(sshPemFileChooser.getSelectedFile() != null){
            userAry[0].setPemFile(sshPemFileChooser.getSelectedFile().getPath());
        }
        userAry[1] = new UserVO();
        userAry[1].setHostName(ftpHostField.getText());
        userAry[1].setPassword(ftpPwField.getText());
        if(ftpPortField.getText().equals("")){
            userAry[1].setPort(21);
        }else{
            userAry[1].setPort(Integer.parseInt(ftpPortField.getText()));
        }
        userAry[1].setUser(ftpUserField.getText());
        if(ftpPemFileChooser.getSelectedFile() != null){
            userAry[1].setPemFile(ftpPemFileChooser.getSelectedFile().getPath());
        }
        return userAry;
    }

    public void actionPerformed(ActionEvent e) {
        String text = ((JButton)e.getSource()).getText();
        if(text.equals("SSH")){
            if(sshFlag)
                return;
            sshFlag = true;
            ftpTgBtn.setSelected(false);
            sshUserField.setVisible(true);
            sshHostField.setVisible(true);
            sshPemFileChooser.setVisible(true);
            sshPwField.setVisible(true);
            sshPortField.setVisible(true);
            ftpUserField.setVisible(false);
            ftpHostField.setVisible(false);
            ftpPemFileChooser.setVisible(false);
            ftpPwField.setVisible(false);
            ftpPortField.setVisible(false);
        }else{
            if(!sshFlag)
                return;
            sshTgBtn.setSelected(false);
            sshUserField.setVisible(false);
            sshHostField.setVisible(false);
            sshPemFileChooser.setVisible(false);
            sshPwField.setVisible(false);
            sshPortField.setVisible(false);
            ftpUserField.setVisible(true);
            ftpHostField.setVisible(true);
            ftpPemFileChooser.setVisible(true);
            ftpPwField.setVisible(true);
            ftpPortField.setVisible(true);
        }
    }
}
