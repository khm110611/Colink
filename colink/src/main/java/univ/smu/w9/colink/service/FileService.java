package univ.smu.w9.colink.service;

import java.io.File;

/**
 * 파일 Service
 * @author "SukHwanYoon"
 *
 */
public class FileService {

    FtpService ftpService;

    SshService sshService;

    /**
     * 파일 서비스 초기화
     * @param ftpService
     * @param sshService
     */
    public FileService(FtpService ftpService, SshService sshService) {
        super();
        this.ftpService = ftpService;
        this.sshService = sshService;
    }

    /**
     * 파일 || 폴더 삭제
     * @param path : 파일 경로
     */
    public void delete(String path){
        File file = new File(path);
        if(file.isDirectory()){
            File[] fileArray = file.listFiles();
            for(File fileItem : fileArray){
                if(fileItem.isFile()){
                    fileItem.delete();
                }else{
                    delete(fileItem.getPath());
                }
                fileItem.delete();
            }
        }else{
            file.delete();
        }
    }

    /**
     * 파일 이름 바꾸기
     * @param path
     * @param reName
     */
    public void reNameFile(String path,String reName){
        File file = new File(path);
        File fileName = new File(reName);
        file.renameTo(fileName);
    }


}
