package univ.smu.w9.guiFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToggleButton;

import univ.smu.w9.colink.service.FileService;
import univ.smu.w9.colink.service.FtpService;
import univ.smu.w9.colink.service.SshService;
import univ.smu.w9.colink.vo.SiteVO;
import univ.smu.w9.colink.vo.UserVO;
import univ.smu.w9.common.CommonGUI;

/**
 * 사이트 관리자 우측
 * @author "SukHwanYoon"
 *
 */
public class MySiteRightPanel extends JPanel implements ActionListener{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7791781084650148997L;
	
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
     * 사이트 이름
     */
    private JTextField siteName;
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
     * Pemfile 선택 버튼
     */
    private JButton pemFileButton;
    
    /**
     * Pem 파일 선택
     */
    private JFileChooser ftpPemFileChooser;

    private JToggleButton sshTgBtn;
    private JToggleButton ftpTgBtn;

    public MySiteRightPanel(){
        sshFlag = true;
        this.ftpService = ftpService;
        this.sshService = sshService;
        this.fileService = fileService;

        sshUser = new UserVO();
        ftpUser = new UserVO();

        this.setVisible(true);
        this.setSize(400, 630);
        this.setLayout(null);
        
        JLabel nameLabel = new JLabel("사이트 이름");
        nameLabel.setLocation(12, 43);
        nameLabel.setSize(72, 12);
        
        siteName = new JTextField();
        siteName.setBounds(101, 35, 259, 30);
        
        JLabel hostLabel = new JLabel("호스트");
        hostLabel.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        hostLabel.setSize(100, 30);
        hostLabel.setLocation(12, 72);

        sshHostField = new JTextField();
        sshHostField.setLocation(72, 72);
        sshHostField.setSize(190, 30);
        sshHostField.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        ftpHostField = new JTextField();
        ftpHostField.setLocation(72, 72);
        ftpHostField.setSize(190, 30);
        ftpHostField.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpHostField.setVisible(false);

        JLabel portLabel = new JLabel("포트");
        portLabel.setSize(35, 30);
        portLabel.setLocation(272, 72);

        sshPortField = new JTextField();
        sshPortField.setSize(41, 30);
        sshPortField.setLocation(319, 73);
        sshPortField.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        ftpPortField = new JTextField();
        ftpPortField.setSize(41, 30);
        ftpPortField.setLocation(319, 72);
        ftpPortField.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpPortField.setVisible(false);

        JLabel userLabel = new JLabel("사용자");
        userLabel.setSize(100, 30);
        userLabel.setLocation(12, 114);
        userLabel.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        sshUserField = new JTextField();
        sshUserField.setSize(288, 30);
        sshUserField.setLocation(72, 112);
        sshUserField.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        ftpUserField = new JTextField();
        ftpUserField.setSize(288, 30);
        ftpUserField.setLocation(72, 112);
        ftpUserField.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpUserField.setVisible(false);

        JLabel pwLabel   = new JLabel("비밀번호");
        pwLabel.setSize(100, 30);
        pwLabel.setLocation(12, 157);
        pwLabel.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        sshPwField = new JTextField();
        sshPwField.setSize(288, 30);
        sshPwField.setLocation(72, 158);
        sshPwField.setFont(CommonGUI.PLAIN_NORMAL_FONT);

        ftpPwField = new JTextField();
        ftpPwField.setSize(288, 30);
        ftpPwField.setLocation(72, 158);
        ftpPwField.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpPwField.setVisible(false);

        pemFileButton = new JButton("파일 선택");
        pemFileButton.setSize(288,30);
        pemFileButton.setLocation(12, 213);
        pemFileButton.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        pemFileButton.addActionListener(this);

        sshTgBtn = new JToggleButton("SSH");
        sshTgBtn.setBounds(12, 10, 135, 23);
        sshTgBtn.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        sshTgBtn.addActionListener(this);
        sshTgBtn.setSelected(true);
        this.add(sshTgBtn);

        ftpTgBtn = new JToggleButton("FTP");
        ftpTgBtn.setBounds(159, 10, 135, 23);
        ftpTgBtn.setFont(CommonGUI.PLAIN_NORMAL_FONT);
        ftpTgBtn.addActionListener(this);
        
        sshPemFileChooser = new JFileChooser();
        ftpPemFileChooser = new JFileChooser();
        this.add(ftpTgBtn);

        this.add(nameLabel);
        this.add(siteName);
        
        this.add(hostLabel);
        this.add(sshHostField);
        this.add(ftpHostField);


        this.add(portLabel);
        this.add(sshPortField);
        this.add(ftpPortField);

        this.add(userLabel);
        this.add(sshUserField);
        this.add(ftpUserField);
        
        this.add(pwLabel);
        this.add(sshPwField);
        this.add(ftpPwField);

        this.add(pemFileButton);
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
    	if(siteName.getText().equals("")){
    		JOptionPane.showMessageDialog(null,"사이트 이름을 입력해주세요.", "저장 실패", JOptionPane.ERROR_MESSAGE);
            return null;
    	}
        if(sshHostField.getText().equals("") || ftpHostField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"호스트 주소를 입력해주세요", "저장 실패", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        if(sshUserField.getText().equals("") || ftpUserField.getText().equals("")){
            JOptionPane.showMessageDialog(null,"유저 ID를 입력해주세요", "저장 실패", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        try{
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
	            userAry[1].setPort(22);
	        }else{
	            userAry[1].setPort(Integer.parseInt(ftpPortField.getText()));
	        }
	        userAry[1].setUser(ftpUserField.getText());
	        if(ftpPemFileChooser.getSelectedFile() != null){
	            userAry[1].setPemFile(ftpPemFileChooser.getSelectedFile().getPath());
	        }
	        return userAry;
        } catch(Exception e){
        	JOptionPane.showMessageDialog(null, "입력 오류 입니다.", "입력 오류", JOptionPane.ERROR_MESSAGE);
        	return null;
        }
    }
    
    public void clearAllField(){
    	this.siteName.setText("");
    	this.ftpHostField.setText("");
    	this.ftpPortField.setText("");
    	this.ftpPwField.setText("");
    	this.ftpUserField.setText("");
    	this.sshHostField.setText("");
    	this.sshPortField.setText("");
    	this.sshUserField.setText("");
    	this.sshPwField.setText("");
    }
    
    public SiteVO returnSiteVO(){
    	SiteVO siteVO = new SiteVO();
    	UserVO[] userAry = this.getUserVO();
    	if(userAry == null){
    		return null;
    	}
    	siteVO.setSiteName(this.siteName.getText());
    	siteVO.setSshSiteAdres(this.sshHostField.getText());
    	siteVO.setFtpSiteAdres(this.ftpHostField.getText());
    	siteVO.setSshUser(userAry[0]);
    	siteVO.setFtpUser(userAry[1]);
    	
    	return siteVO;
    }
    
    /**
     * 사이트 정보 저장
     * @param siteVO
     */
    public void setSiteVo(SiteVO siteVO){
    	this.siteName.setText(siteVO.getSiteName());
    	this.siteName.updateUI();
    	this.sshHostField.setText(siteVO.getSshSiteAdres());
    	this.ftpHostField.setText(siteVO.getFtpSiteAdres());
    	this.sshUserField.setText(siteVO.getSshUser().getUser());
    	this.sshPwField.setText(siteVO.getSshUser().getPassword());
    	this.sshPortField.setText(siteVO.getSshUser().getPort()+"");
    	this.ftpUserField.setText(siteVO.getFtpUser().getUser());
    	this.ftpPwField.setText(siteVO.getFtpUser().getPassword());
    	this.ftpPortField.setText(siteVO.getFtpUser().getPort()+"");
    	this.updateUI();
    }
    
    public void actionPerformed(ActionEvent e) {
        String text = ((AbstractButton)e.getSource()).getText();
        if(text.equals("SSH")){
        	sshTgBtn.setSelected(true);
        	ftpTgBtn.setSelected(false);
            if(sshFlag)
                return;
            sshFlag = true;
            
            sshUserField.setVisible(true);
            sshHostField.setVisible(true);
            sshPwField.setVisible(true);
            sshPortField.setVisible(true);
            ftpUserField.setVisible(false);
            ftpHostField.setVisible(false);
            ftpPwField.setVisible(false);
            ftpPortField.setVisible(false);
        }else if(text.equals("FTP")){
        	ftpTgBtn.setSelected(true);
        	sshTgBtn.setSelected(false);
            if(!sshFlag)
                return;
            sshFlag = false;
            sshUserField.setVisible(false);
            sshHostField.setVisible(false);
            sshPwField.setVisible(false);
            sshPortField.setVisible(false);
            ftpUserField.setVisible(true);
            ftpHostField.setVisible(true);
            ftpPwField.setVisible(true);
            ftpPortField.setVisible(true);
        }else if(text.equals("파일 선택")){
        	if(sshFlag){
        		sshPemFileChooser.showOpenDialog(this);
        	}else{
        		ftpPemFileChooser.showOpenDialog(this);
        	}
        }
    }
}
