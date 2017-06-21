package univ.smu.w9.colink.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import univ.smu.w9.colink.vo.UserVO;

/**
 * ssh service
 * @author "SukHwanYoon"
 *
 */
public class SshService {

    // 연결 객체
    JSch jsch;

    // 연결 session
    Session session;

    // 연결 channel
    Channel channel;

    // 명령어 실행 channel
    ChannelExec channelExec;

    // 사용자
    UserVO sshUser;

    // pem file
    String privateKey;

    /**
     * SSH service init
     * @param sshUser : ssh 사용자 정보
     */
    public SshService(UserVO sshUser){
        jsch = new JSch();
        this.sshUser = sshUser;
    }

    /**
     * SSH Connect
     */
    public void connect(){

        try {
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Pem file 을 사용한 connect
     * @param privateKey : pem File
     * @throws IOException
     */
    public void connect(String privateKey) throws IOException{

        try {
            this.privateKey = privateKey;
            // 세션 객체 생성
            jsch.addIdentity(privateKey);
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
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *  SSH disconnect
     */
    public void disconnect(){
        channelExec.disconnect();
        channel.disconnect();
        session.disconnect();
    }

    /**
     * 명령어 전송
     * @param exec : 명령어
     */
    public void sendExec(String exec){
        // 체널이 연결상태일때
        if(channelExec.isConnected()){
            channelExec.setCommand(exec);
        }
        // 연결종료시
        else{

        }

    }
}
