package univ.smu.w9.colink.service;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import com.jcraft.jsch.JSchException;

import univ.smu.w9.colink.vo.UserVO;
import univ.smu.w9.common.CommonString;

public class SshServiceTest{
    UserVO sshUser;
    SshService sshService;

    @Before
    public void initTest(){
        sshUser = new UserVO();
        sshUser.setHostName("ec2-13-124-89-33.ap-northeast-2.compute.amazonaws.com");
        sshUser.setPassword("");
        sshUser.setPort(22);
        sshUser.setUser("ubuntu");

        sshService = new SshService(sshUser);
    }

    public void testConnect() throws IOException {
        sshService.connect();
        sshService.disConnect();
    }

    @Test
    public void testPemConnect() throws IOException, JSchException {
        sshService.connect(CommonString.DESKTOP_PATH+"/sukhwan.ppk");
        sshService.sendExec("ls -al");
        sshService.disConnect();
    }
    @Test
    public void testDisconnect() {
    }

    @Test
    public void testSendExec() {
    }

}
