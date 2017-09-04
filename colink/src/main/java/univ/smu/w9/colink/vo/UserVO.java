package univ.smu.w9.colink.vo;

/**
 * 유저 VO
 * hostName   : 호스트 주소
 * user       : 유저 이름
 * password   : 비밀번호
 * port       : 포트번호
 *
 * @author "SukHwanYoon"
 *
 */
public class UserVO {
    private String hostName;
    private String user;
    private String password;
    private Integer port;
    private String pemFile;


    public String getPemFile() {
        return pemFile;
    }
    public void setPemFile(String pemFile) {
        this.pemFile = pemFile;
    }
    public void setPort(Integer port) {
        this.port = port;
    }
    public UserVO() {
        super();
    }
    public UserVO(String hostName, String user, String password, Integer port) {
        super();
        this.hostName = hostName;
        this.user = user;
        this.password = password;
        this.port = port;
    }
    public String getHostName() {
        return hostName;
    }
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }
    public String getUser() {
        return user;
    }
    public void setUser(String user) {
        this.user = user;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getPort() {
        return port;
    }
    public void setPort(int port) {
        this.port = port;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.hostName+",");
        sb.append(this.user+",");
        if(this.password.equals("")){
        	this.password = " ";
        }
        sb.append(this.password+",");
        sb.append(this.port);
        return sb.toString();
    }

}
