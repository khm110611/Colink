package univ.smu.w9.colink.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import univ.smu.w9.colink.vo.UserVO;

/**
 * FTP Service
 * @author "SukHwanYoon"
 *
 */
public class FtpService {

     // 연결 객체
    JSch jsch;

    // 연결 session
    Session session;

    // 연결 channel
    Channel channel;

    // FTP channel
    ChannelSftp channelSFtp;
    // 사용자
    UserVO ftpUser;

    /**
     * FTP Service Init
     * @param ftpUser : FTP 사용자
     */
    public FtpService(UserVO ftpUser){
        jsch = new JSch();
        this.ftpUser = ftpUser;
    }

    /**
     *  SFTP Connect
     */
    public void connect(){

        try {
            // 세션 객체 생성
            session = jsch.getSession(ftpUser.getUser(),ftpUser.getHostName(),ftpUser.getPort());

            // 비밀번호 설정
            session.setPassword(ftpUser.getPassword());

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
            channelSFtp = (ChannelSftp)channel;
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * SFTP disconnect
     */
    public void disconnect(){
        channelSFtp.disconnect();
        channel.disconnect();
        session.disconnect();
    }

    /**
     * 파일 업로드
     * @param catalinaHome : 파일경로
     * @param file : 파일
     */
    public void upload(String catalinaHome,File file){
        if(channelSFtp.isConnected()){
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(file);

                // 경로이동
                channelSFtp.cd(catalinaHome);

                //파일 업로드
                channelSFtp.put(fis,file.getName());
            }
            // 파일 존재하지 않을때
            catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SftpException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                try {
                    // FileInputStream 종료
                    fis.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }else{

        }
    }
}
