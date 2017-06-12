package univ.smu.w9.colink.service;

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

    // Init SshService
    public SshService(UserVO sshUser){
        jsch = new JSch();
        this.sshUser = sshUser;
    }

    // SSH Connect
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
            Channel channel = session.openChannel("exec");

            // ssh 채널 객체로 캐스팅
            channelExec = (ChannelExec)channel;
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // SSH disconnect
    public void disconnect(){
        channelExec.disconnect();
        channel.disconnect();
        session.disconnect();
    }
}
