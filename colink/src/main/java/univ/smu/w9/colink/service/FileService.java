package univ.smu.w9.colink.service;

import java.io.File;

/**
 * 파일 Service
 * @author "SukHwanYoon"
 *
 */
public class FileService {



    /**
     * 파일 서비스 초기화
     */
    public FileService() {
        super();
    }

    /**
     * 특정위치 파일/폴더 목록 가져오기
     * @param path
     * @return
     */
    public File[] search(String path){
        return new File(path).listFiles();
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
