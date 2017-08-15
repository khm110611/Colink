package univ.smu.w9.colink.vo;

/**
 * 사이트 저장 정보
 * @author "SukHwanYoon"
 *
 */
public class SiteVO {

    /**
     * 사이트 이름
     */
    private String siteName;

    /**
     * ssh 접속 주소
     */
    private String sshSiteAdres;

    /**
     * ssh 접속 정보
     */
    private UserVO sshUser;


    /**
     * ftp 접속 주소
     */
    private String ftpSiteAdres;

    /**
     * ftp 접속 정보
     */
    private UserVO ftpUser;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSshSiteAdres() {
        return sshSiteAdres;
    }

    public void setSshSiteAdres(String sshSiteAdres) {
        this.sshSiteAdres = sshSiteAdres;
    }

    public UserVO getSshUser() {
        return sshUser;
    }

    public void setSshUser(UserVO sshUser) {
        this.sshUser = sshUser;
    }

    public String getFtpSiteAdres() {
        return ftpSiteAdres;
    }

    public void setFtpSiteAdres(String ftpSiteAdres) {
        this.ftpSiteAdres = ftpSiteAdres;
    }

    public UserVO getFtpUser() {
        return ftpUser;
    }

    public void setFtpUser(UserVO ftpUser) {
        this.ftpUser = ftpUser;
    }

    public SiteVO() {
		super();
	}

	public SiteVO(String siteName, String sshSiteAdres,String ftpSiteAdres) {
        super();
        this.siteName = siteName;
        this.sshSiteAdres = sshSiteAdres;
        this.ftpSiteAdres = ftpSiteAdres;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.siteName+",");
        sb.append(this.sshSiteAdres+",");
        sb.append(this.ftpSiteAdres+",");
        sb.append(this.ftpUser.toString()+",");
        sb.append(this.sshUser.toString());
        return sb.toString();
    }


}
