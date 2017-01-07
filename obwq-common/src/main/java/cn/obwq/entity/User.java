package cn.obwq.entity;

import java.util.Date;



public class User  extends BaseDo {

	private static final long serialVersionUID = -71677643924169035L;

	private String nickName ;
	
	private String userId ; 
	
	private String email ;
	
	private String password ;
	//用户类型 UserEume
	private Integer userType ;
	//外部用户ID	
	private String outUserId ;
	
	private String userPicture ;
	
	private Date gmtCreate ;
	
	private Date gmtModifidy ;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModifidy() {
		return gmtModifidy;
	}

	public void setGmtModifidy(Date gmtModifidy) {
		this.gmtModifidy = gmtModifidy;
	}

	public Integer getUserType() {
		return userType;
	}

	public String getOutUserId() {
		return outUserId;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public void setOutUserId(String outUserId) {
		this.outUserId = outUserId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserPicture() {
		return userPicture;
	}

	public void setUserPicture(String userPicture) {
		this.userPicture = userPicture;
	}
	
	
	
}