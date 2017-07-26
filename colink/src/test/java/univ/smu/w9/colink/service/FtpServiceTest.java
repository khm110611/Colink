package univ.smu.w9.colink.service;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.jcraft.jsch.JSchException;

import univ.smu.w9.colink.vo.UserVO;
import univ.smu.w9.common.CommonString;

public class FtpServiceTest {
	
    UserVO ftpUser;
    FtpService ftpService;

    @Before
    public void initTest(){
    	ftpUser = new UserVO();
    	ftpUser.setHostName("ec2-13-124-89-33.ap-northeast-2.compute.amazonaws.com");
        ftpUser.setPassword("");
        ftpUser.setPort(22);
        ftpUser.setUser("ubuntu");

        ftpService = new FtpService(ftpUser);
    }

    public void testConnect() throws IOException {
    	ftpService.connect();
        ftpService.disconnect();
    }

    @Test
    public void testPemConnect() throws IOException, JSchException {
    	ftpService.connect(CommonString.DESKTOP_PATH+"/sukhwan.ppk");
    	ftpService.upload("/home/ubuntu",new File(CommonString.DESKTOP_PATH+"/sukhwan.ppk"));
    	ftpService.disconnect();
    }
    @Test
    public void testDisconnect() {
    }

    @Test
    public void testSendExec() {
    }
}
