package univ.smu.w9.colink.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;

import univ.smu.w9.colink.common.CommonString;
import univ.smu.w9.colink.vo.SiteVO;
import univ.smu.w9.colink.vo.UserVO;

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

    /**
     * 파일에 저장된 사이트 리스트 가져오기
     * @return
     * @throws IOException
     */
    public List<SiteVO> getSiteList(){
    	List<SiteVO> siteList = new ArrayList<SiteVO>();
        File siteFile = new File(CommonString.SITE_FILE_PATH);
        FileReader fileReader;
        try {
            fileReader = new FileReader(siteFile);
        } catch (FileNotFoundException e) {
            return new ArrayList<SiteVO>();
        }
        try{
	        BufferedReader fileBufReader = new BufferedReader(fileReader);
	        String line;
	        StringTokenizer st;
	        UserVO ftpUser;
	        UserVO sshUser;
	        SiteVO siteVO;
	        while((line = fileBufReader.readLine()) != null){
	            st = new StringTokenizer(line, ",");
	            siteVO = new SiteVO(st.nextToken(),
	                    st.nextToken(),                    
	                    st.nextToken()
	                    );
	            ftpUser = new UserVO(st.nextToken()
	                    ,st.nextToken()
	                    ,st.nextToken()
	                    ,Integer.parseInt(st.nextToken())
	                    ,st.nextToken());
	            sshUser = new UserVO(st.nextToken()
	                    ,st.nextToken()
	                    ,st.nextToken()
	                    ,Integer.parseInt(st.nextToken())
	                    ,st.nextToken());
	            siteVO.setFtpUser(ftpUser);
	            siteVO.setSshUser(sshUser);
	            siteList.add(siteVO);
	        }
	        fileBufReader.close();
	        fileReader.close();
	        return siteList;
    	} catch(Exception e){
    		JOptionPane.showMessageDialog(null, "파일불러오기에 실패했습니다.", "파일불러오기 오류", JOptionPane.ERROR_MESSAGE);
    		e.printStackTrace();
    		return new ArrayList<SiteVO>();
    	}
    }

    /**
     * 사이트 리스트 파일에 저장
     * @throws IOException
     */
    public void saveSiteList(List<SiteVO> siteList) throws IOException{
    	File folder = new File(CommonString.FOLDER_PATH);
    	if(!folder.exists()){
    		folder.mkdirs();
    	}
        File siteFile = new File(CommonString.SITE_FILE_PATH);
        siteFile.delete();

        BufferedWriter bw = new BufferedWriter(new FileWriter(CommonString.SITE_FILE_PATH));
        Iterator<SiteVO> iterator = siteList.iterator();
        while(iterator.hasNext()){
            bw.write(iterator.next().toString()+"\n");
        }
        bw.close();
    }
}
