package univ.smu.w9.colink.service;

import org.junit.Before;
import org.junit.Test;

import univ.smu.w9.colink.vo.UserVO;

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

    @Test
    public void testConnect() {
        sshService.connect();
    }

    @Test
    public void testDisconnect() {
    }

    @Test
    public void testSendExec() {
    }

}
