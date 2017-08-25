package univ.smu.w9.colink.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Properties;
import java.util.Vector;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import univ.smu.w9.colink.guiComponent.MyFileJTree;
import univ.smu.w9.colink.guiComponent.MyFolderJTree;
import univ.smu.w9.colink.vo.UserVO;

/**
 * FTP Service
 * @author "SukHwanYoon"
 *
 */
public class FtpService {

    // 연결 객체
    private JSch jsch;

    // 연결 session
    private Session session;

    // 연결 channel
    private Channel channel;

    // FTP channel
    private ChannelSftp channelSFtp;

    // 사용자
    private UserVO ftpUser;

    // pem file
    private String privateKey;

    // 현재 사이트 pemFile사용 유무
    private boolean pemYn;

    private MyFolderJTree ftpFolderTree;
    private MyFileJTree ftpFileTree;
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
        if(ftpUser.getPemFile() != null){
            connect(ftpUser.getPemFile());
            return;
        }
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
            channel = session.openChannel("ftp");
            channel.connect();
            // ssh 채널 객체로 캐스팅
            channelSFtp = (ChannelSftp)channel;
            ftpFolderTree.setByVector("/home", this.getFolderList("/home"));
            ftpFileTree.setByVector("/home", this.getFileList("/home"));
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     *  SFTP Connect
     */
    public void connect(String privateKey){

        try {
            this.privateKey = privateKey;
            // 세션 객체 생성
            jsch.addIdentity(privateKey);
            // 세션 객체 생성
            session = jsch.getSession(ftpUser.getUser(),ftpUser.getHostName(),ftpUser.getPort());

            //세션 관련 정보 설정
            Properties config = new Properties();

            // 호스트 정보 검사 x
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);

            // 접속
            session.connect();

            // sftp 채널 open
            channel = session.openChannel("sftp");
            channel.connect();
            // ssh 채널 객체로 캐스팅
            channelSFtp = (ChannelSftp)channel;

            ftpFolderTree.setByVector("/home", this.getFileList("/home"));
            ftpFileTree.setByVector("/home", this.getFileList("/home"));
        } catch (JSchException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * SFTP disconnect
     */
    public void disConnect(){
        channelSFtp.disconnect();
        channel.disconnect();
        session.disconnect();
    }

    /**
     * 재연결
     * @throws IOException
     */
    public boolean reConnect(){
        if(this.ftpUser == null)
            return false;
        if(pemYn){
            this.connect(privateKey);
        }else{
            this.connect();
        }
        return true;
    }

    /**
     * 파일 업로드
     * @param catalinaHome : 파일경로
     * @param file : 파일
     * @throws IOException
     */
    public void upload(String catalinaHome,File file){
        this.connect();
        if(session.isConnected()){
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
            //연결 x
        }
    }

    /**
     * 하나의 파일을 다운로드 한다.
     *
     * @param dir : 저장할 경로(서버)
     * @param downloadFileName : 다운로드할 파일
     * @param path : 저장될 공간
     */
    public void download(String dir, String downloadFileName, String path) {
        InputStream in = null;
        FileOutputStream out = null;
        try {
            channelSFtp.cd(dir);
            in = channelSFtp.get(downloadFileName);
        } catch (SftpException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            out = new FileOutputStream(new File(path));
            int i;

            while ((i = in.read()) != -1) {
                out.write(i);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
    /**
     * 파일 리스트 가져오기
     * @param catalinaHome : 경로
     * @return
     */
    public Vector getFileList(String catalinaHome){
        try {
            channelSFtp.cd(catalinaHome);
            Vector<ChannelSftp.LsEntry> vector = channelSFtp.ls(".");

            Iterator<ChannelSftp.LsEntry> iterator = vector.iterator();
            Vector<ChannelSftp.LsEntry> fileList = new Vector<ChannelSftp.LsEntry>();
            ChannelSftp.LsEntry buf;
            while(iterator.hasNext()){
                buf = iterator.next();
                if(!buf.getAttrs().isDir()){
                    fileList.add(buf);
                }
            }
            return fileList;
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 파일 리스트 가져오기
     * @param catalinaHome : 경로
     * @return
     */
    public Vector getFolderList(String catalinaHome){
        try {
            channelSFtp.cd(catalinaHome);
            Vector<ChannelSftp.LsEntry> vector = channelSFtp.ls(".");

            Iterator<ChannelSftp.LsEntry> iterator = vector.iterator();
            Vector<ChannelSftp.LsEntry> folderList = new Vector<ChannelSftp.LsEntry>();
            ChannelSftp.LsEntry buf;
            while(iterator.hasNext()){
                buf = iterator.next();
                if(!buf.getFilename().equals(".") && buf.getAttrs().isDir()){
                    folderList.add(buf);
                }
            }
            return folderList;
        } catch (SftpException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * FTP 유저 setter
     * @param ftpUser : ftp 유저 정보
     */
    public void setFtpUser(UserVO ftpUser) {
        this.ftpUser = ftpUser;
    }


    /**
     * 폴더 트리
     * @param ftpFolderTree
     */
    public void setFtpFolderTree(MyFolderJTree ftpFolderTree) {
        this.ftpFolderTree = ftpFolderTree;
    }

    /**
     * 파일 트리
     * @param ftpFileTree
     */
    public void setFtpFileTree(MyFileJTree ftpFileTree) {
        this.ftpFileTree = ftpFileTree;
    }


}
