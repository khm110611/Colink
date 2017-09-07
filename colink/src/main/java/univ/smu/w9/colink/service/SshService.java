package univ.smu.w9.colink.service;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import univ.smu.w9.colink.guiComponent.MySshArea;
import univ.smu.w9.colink.vo.UserVO;
import univ.smu.w9.common.CommonString;

/**
 * ssh service
 * @author "SukHwanYoon"
 *
 */
public class SshService implements Runnable{

    // 연결 객체
    private JSch jsch;

    // 연결 session
    private Session session;

    // 연결 channel
    private Channel channel;

    // 명령어 실행 channel
    private ChannelExec channelExec;

    // 사용자
    private UserVO sshUser;

    // pem file
    private String privateKey;

    // My sshArea
    private MySshArea mySshArea;
    private JScrollPane jscroll;
    // 현재 사이트 pemFile사용 유무
    private boolean pemYn;

    private String exec;
    
    private String cdPath;
    
    /**
     *
     * SSH service init
     * @param sshUser : ssh 사용자 정보
     */
    public SshService(UserVO sshUser){
        jsch = new JSch();
        this.sshUser = sshUser;
        pemYn = false;
    }

    /**
     * SSH Connect
     */
    public void connect(){
    	if(this.cdPath == null){
    		this.cdPath = "/home";
    	}
        if(sshUser.getPemFile() != null){
            connect(sshUser.getPemFile());
            return;
        }
        try {
            pemYn = false;

            // 세션 객체 생성
            session = jsch.getSession(sshUser.getUser(),sshUser.getHostName(),sshUser.getPort());

            // 비밀번호 설정
            session.setPassword(sshUser.getPassword());

            //세션 관련 정보 설정
            Properties config = new Properties();

            // 호스트 정보 검사 x
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // 접속
            session.connect();

            // sftp 채널 open
            channel = session.openChannel("exec");

            // ssh 채널 객체로 캐스팅
            channelExec = (ChannelExec)channel;
        } catch (JSchException e) {
            JOptionPane.showMessageDialog(null, e.getCause(), "SSH 연결 실패", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    /**
     * Pem file 을 사용한 connect
     * @param privateKey : pem File
     * @throws IOException
     */
    public void connect(String privateKey){
        try {
            pemYn = true;

            this.privateKey = privateKey;
            // 세션 객체 생성
            jsch.addIdentity(privateKey);
            session = jsch.getSession(sshUser.getUser(),sshUser.getHostName(),sshUser.getPort());

            //세션 관련 정보 설정
            Properties config = new Properties();

            // 호스트 정보 검사 x
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // 접속
            session.connect();

            // ssh 채널 open
            channel = session.openChannel("exec");

            // ssh 채널 객체로 캐스팅
            channelExec = (ChannelExec)channel;

        } catch (JSchException e) {
            JOptionPane.showMessageDialog(null,e.getCause(), "SSH 연결 실패", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * 재연결
     * @throws IOException
     */
    public boolean reConnect(){
        if(this.sshUser == null)
            return false;
        if(pemYn){
            this.connect(privateKey);
        }else{
            this.connect();
        }
        return true;
    }

    /**
     *  SSH disconnect
     */
    public void disConnect(){
        channelExec.disconnect();
        channel.disconnect();
        session.disconnect();
    }

    /**
     * 명령어 전송
     * @param exec : 명령어
     * @throws JSchException
     * @throws IOException
     */
    public synchronized void sendExec(String exec){
        //ssh 연결중일때
        if(session != null && session.isConnected()){
            this.exec = exec;
            Thread thread = new Thread(this);
            thread.run();
            thread.interrupt();
        }
        // 연결 해지시
        else{
            JOptionPane.showMessageDialog(null, "SSH 연결이 필요합니다.", "SSH 연결 실패", JOptionPane.ERROR_MESSAGE);
        }

    }


    /**
     * userVO setter
     * @param sshUser
     */
    public void setSshUser(UserVO sshUser) {
        this.sshUser = sshUser;
    }

    /**
     * mySSH area setter
     * @param mySSHArea
     */
    public void setMySshArea(MySshArea mySshArea){
        this.mySshArea = mySshArea;
    }

    public void run() {
        this.connect();
        channelExec.setCommand("cd "+cdPath+"\n"+exec);
        if(exec.equals("clear")){
            mySshArea.setText("");
            mySshArea.updateUI();

            jscroll.updateUI();
        }else if(exec.contains("cd")){
        	exec = exec.substring(exec.indexOf("cd")+3,exec.length());
        	if(exec.contains("..")){
        		this.cdPath = this.cdPath.substring(0,this.cdPath.lastIndexOf("/")-1);
        	}else if(exec.equals("/")){
        		this.cdPath = "/";
        	}else{
        		this.cdPath = this.cdPath+"/"+exec;
        	}
    	}else{
            try {
                channelExec.connect();
                BufferedReader br = new BufferedReader(new InputStreamReader(channel.getInputStream(), "UTF-8"));
                String str;
                while((str = br.readLine()) != null){
                    mySshArea.append(str+"\n");
                }
                br.readLine();

            } catch (JSchException e) {
                JOptionPane.showMessageDialog(null, e.getCause(), "SSH 연결 실패", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, e.getCause(), "SSH 연결 실패", JOptionPane.ERROR_MESSAGE);
            }finally {
                channelExec.disconnect();
                mySshArea.updateUI();
                mySshArea.setBackground(Color.BLACK);
                mySshArea.setForeground(Color.WHITE);
                jscroll.updateUI();
                jscroll.getVerticalScrollBar().setValue(mySshArea.getHeight());
                jscroll.getVerticalScrollBar().updateUI();
            }
        }
    }

    public void setJscroll(JScrollPane jscroll) {
        this.jscroll = jscroll;
    }

	public String getCdPath() {
		return cdPath;
	}

	public void setCdPath(String cdPath) {
		this.cdPath = cdPath;
	}


}
