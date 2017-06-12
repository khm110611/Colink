package univ.smu.w9.colink.main;

import univ.smu.w9.colink.gui.FtpMain;
import univ.smu.w9.colink.vo.UserVO;

public class Main {

    FtpMain ftpMain;

    //ssh 연결 정보
    UserVO sshUser;

    //ftp 연결 정보
    UserVO ftpUser;


    public Main() {

    }

    public static void main(String[] args) {
        new Main();
    }

}
