package cn.obwq.entity;


public class UserGroup extends BaseDo {

	private static final long serialVersionUID = -1981633546326298086L;

	
	private Long id ;

	private String userId ; 
	
	private Long groupId ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
}
